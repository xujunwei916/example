package com.example.others.auto.jiraTest;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionReport {

  static Logger log = LoggerFactory.getLogger(ActionReport.class);

  public static void main(String[] args) throws Exception {

    ZipOutputStream zipOut = new ZipOutputStream(
        new FileOutputStream("D:\\jira\\auto\\155\\cpo\\MODELS.zip"));

    ActionReport.aipReportConversion(zipOut, new File("D:\\jira\\auto\\155\\cpo\\cpo"),
        new File("D:\\jira\\auto\\155\\cpo\\freq"));

    IOUtils.closeQuietly(zipOut);
  }

  public static FilenameFilter FILENAME_FLITER = new FilenameFilter() {
    @Override
    public boolean accept(File dir, String name) {
      return !name.endsWith(".split") && !name.endsWith(".zip");
    }
  };

  public static void aipReportConversion(ZipOutputStream zipOut, File cpo, File freq)
      throws Exception {
    zipOut.putNextEntry(new ZipEntry("aip.csv"));
    List<File> cpoFiles = listFiles(cpo);
    List<File> freqFiles = listFiles(freq);
    aipReportConversion(zipOut, cpoFiles, freqFiles);
  }

  public static List<File> listFiles(File file) {
    List<File> files = new ArrayList<>();
    if (file.isFile()) {
      files.add(file);
    } else if (file.isDirectory()) {
      for (File f : file.listFiles(FILENAME_FLITER)) {
        files.addAll(listFiles(f));
      }
    }
    return files;
  }


  public static void aipReportConversion(ZipOutputStream zipOut, List<File> attrs, List<File> freqs)
      throws IOException {

    // cpo 报告 models
    String[] models = {"IMP_IN_ANYTIME", "LAST_IMP", "IMP_THEN_CLK",
        "CLK_IN_ANYTIME", "LAST_CLICK", "LINEAR", "01", "02", "03", "04", "05",
        "06", "07", "08", "09", "10", "10+"};

    String[] rowNames = {"TargetAudience", "Impression", "ImpressionUV",
        "Clicks", "Uniqueclickers", "CTR", "Purchaser", "PurchaserRate",
        "Transaction", "Sales", "ATV", "MediaCost", "CPO", "CPA",
        "Incrementalsales", "IncrementalROI", " "};

    Set<String> set = Sets.newHashSet("Impression", "ImpressionUV",
        "Clicks", "Uniqueclickers", "Purchaser", "Transaction",
        "Sales");

    String[] modelAlias = {"曝光且购买", "先曝光再购买", "先曝光再点击再购买", "点击且购买",
        "先点击再购买", "线性归因", "曝光1次再购买", "曝光2次再购买", "曝光3次再购买", "曝光4次再购买", "曝光5次再购买",
        "曝光6次再购买", "曝光7次再购买", "曝光8次再购买", "曝光9次再购买", "曝光10次再购买",
        "曝光10次以上再购买"};

    List<String> ID_LIST = Lists.newArrayList();

    List<String> ID_NAMES = ID_LIST;

    HashBasedTable<String, String, String> table = HashBasedTable.create();

    for (File attrFile : attrs) {
      readAttrFile(table, attrFile, ID_LIST);
    }

    for (File freqFile : freqs) {
      readFreqFile(table, freqFile, ID_LIST);
    }

    CSVWriter writer = new CSVWriter(new OutputStreamWriter(zipOut, "utf-8"));

    List<String> list = Lists.newArrayList();
    list.add("");
    list.add("");
    for (String name : ID_NAMES) {
      list.add(name);
    }

    String[] titles = list.toArray(new String[list.size()]);
    writer.writeNext(titles);

    for (int i = 0; i < models.length; i++) {
      String model = models[i];
      String alias = modelAlias[i];

      for (String rowName : rowNames) {
        List<String> row = Lists.newArrayList();
        row.add(alias);
        row.add(rowName);

        for (String id : ID_LIST) {
          String value = table.get(model,
              String.format("%s_%s", rowName, id));
          if (StringUtils.isBlank(id)) {
            row.add("");
          } else if (set.contains(rowName)) {
            row.add(value == null ? "0" : value);
          } else {
            row.add(value == null ? "" : value);
          }
        }

        writer.writeNext(row.toArray(new String[row.size()]));
      }
    }
    writer.close();
  }

  private static void readFreqFile(
      HashBasedTable<String, String, String> table, File file, List<String> idList)
      throws IOException {
    CSVReader reader = new CSVReader(new FileReader(file));
    // dim,id,model,Purchaser,Transaction,Sales

    DecimalFormat df = new DecimalFormat("00");

    String[] line = null;
    while ((line = reader.readNext()) != null) {
      int idx = 0;
      String id = line[idx++].replaceAll("[^\u4e00-\u9fa5_a-zA-Z0-9]", "");
      String model = line[idx++];
      String impPv = line[idx++];
      String impUv = line[idx++];
      String clkPv = line[idx++];
      String clkUv = line[idx++];

      if (!model.contains("+")) {
        model = df.format(Double.valueOf(model));
      } else if ("11+".equals(model)) {
        model = "10+";
      }
      if (!idList.contains(id)) {
        idList.add(id);
      }

      table.put(model, String.format("%s_%s", "Impression", id),
          getOrDefault(impPv, "0"));
      table.put(model, String.format("%s_%s", "ImpressionUV", id),
          getOrDefault(impUv, "0"));
      table.put(model, String.format("%s_%s", "Clicks", id),
          getOrDefault(clkPv, "0"));
      table.put(model, String.format("%s_%s", "Uniqueclickers", id),
          getOrDefault(clkUv, "0"));
    }

    String[] models = {"IMP_IN_ANYTIME", "LAST_IMP", "IMP_THEN_CLK",
        "CLK_IN_ANYTIME", "LAST_CLICK", "LINEAR"};
    for (String model : models) {
      Map<String, String> row = table.row("1+");
      for (String column : row.keySet()) {
        table.put(model, column, row.get(column));
      }
    }

    IOUtils.closeQuietly(reader);
  }

  private static void readAttrFile(
      HashBasedTable<String, String, String> table, File file, List<String> idList)
      throws IOException {
    CSVReader reader = new CSVReader(new FileReader(file));
    String[] line = null;

    // dim,id,model,Purchaser,Transaction,Sales

    boolean readHeader = false;
    while ((line = reader.readNext()) != null) {
      if (!readHeader) {
        readHeader = true;
        continue;
      }
      String id = line[0];
      String dimension = line[1];
      String model = line[2];
      String purchaser = getOrDefault(line[3], "0");
      String transaction = getOrDefault(line[4], "0");
      String sales = getOrDefault(line[5], "0");

      if ("11+".equals(model)) {
        model = "10+";
      }
      if (!idList.contains(dimension)) {
        idList.add(dimension);
      }

      table.put(model, String.format("%s_%s", "Purchaser", dimension),
          purchaser);
      table.put(model, String.format("%s_%s", "Transaction", dimension),
          transaction);
      table.put(model, String.format("%s_%s", "Sales", dimension), sales);
    }
    IOUtils.closeQuietly(reader);
  }

  public static String getOrDefault(String str, String defaultValue) {
    return StringUtils.isNotBlank(str) ? str : "0";
  }

}
