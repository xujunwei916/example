package com.example.others.zip;

import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class CpoZipFreqReport {


  public static final List<String> freqModels = new ArrayList<>();
  public static final List<String> freqModels2 = new ArrayList<>();

  static {
    freqModels.addAll(
        Arrays.asList(
            new String[]{"01__MODELS", "02__MODELS", "03__MODELS", "04__MODELS", "05__MODELS",
                "06__MODELS", "07__MODELS", "08__MODELS", "09__MODELS", "10__MODELS",
                "10+__MODELS"}));
    freqModels2.addAll(
        Arrays.asList(
            new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11+"}));
  }


  public static void main(String[] args) throws Exception {
//    File file = new File("D:\\jira\\loreal\\freq_report\\custom_1943.zip");
//    readAttr("1943", new File("D:\\jira\\loreal\\freq_report\\custom_1943.zip"));
//    readAttr("1944", new File("D:\\jira\\loreal\\freq_report\\custom_1944.zip"));
//    readAttr("1945", new File("D:\\jira\\loreal\\freq_report\\custom_1945.zip"));
//    readAttr("1946", new File("D:\\jira\\loreal\\freq_report\\custom_1946.zip"));
//    readAttr("1947", new File("D:\\jira\\loreal\\freq_report\\custom_1947.zip"));
//    readAttr("1948", new File("D:\\jira\\loreal\\freq_report\\custom_1948.zip"));
//    readAttr("1949", new File("D:\\jira\\loreal\\freq_report\\custom_1949.zip"));
//    readAttr("1950", new File("D:\\jira\\loreal\\freq_report\\custom_1950.zip"));

    readAttr("1955", new File("D:\\jira\\loreal\\freq_report\\custom_1955.zip"));
    readAttr("1956", new File("D:\\jira\\loreal\\freq_report\\custom_1956.zip"));
    readAttr("1958", new File("D:\\jira\\loreal\\freq_report\\custom_1958.zip"));
    readAttr("1959", new File("D:\\jira\\loreal\\freq_report\\custom_1959.zip"));
    readAttr("1960", new File("D:\\jira\\loreal\\freq_report\\custom_1960.zip"));

//    readFreq("1943",new File("D:\\jira\\loreal\\freq_report\\custom_1943_report.zip"));
//    readFreq("1944",new File("D:\\jira\\loreal\\freq_report\\custom_1944_report.zip"));
//    readFreq("1945",new File("D:\\jira\\loreal\\freq_report\\custom_1945_report.zip"));
//    readFreq("1946",new File("D:\\jira\\loreal\\freq_report\\custom_1946_report.zip"));
//    readFreq("1947",new File("D:\\jira\\loreal\\freq_report\\custom_1947_report.zip"));
//    readFreq("1948",new File("D:\\jira\\loreal\\freq_report\\custom_1948_report.zip"));
//    readFreq("1949",new File("D:\\jira\\loreal\\freq_report\\custom_1949_report.zip"));
//    readFreq("1950",new File("D:\\jira\\loreal\\freq_report\\custom_1950_report.zip"));

    readFreq("1955", new File("D:\\jira\\loreal\\freq_report\\custom_1955_report.zip"));
    readFreq("1956", new File("D:\\jira\\loreal\\freq_report\\custom_1956_report.zip"));
    readFreq("1958", new File("D:\\jira\\loreal\\freq_report\\custom_1958_report.zip"));
    readFreq("1959", new File("D:\\jira\\loreal\\freq_report\\custom_1959_report.zip"));
    readFreq("1960", new File("D:\\jira\\loreal\\freq_report\\custom_1960_report.zip"));

  }


  public static void readFreq(String task, File file) throws Exception {
    Map<String, String> result = new HashMap<>();
    String resultLine[] = new String[11];
    ZipInputStream zis = null;
    try {
      zis = new ZipInputStream(new FileInputStream(file));

      ZipEntry entry = null;

      while ((entry = zis.getNextEntry()) != null) {
        CSVReader reader = new CSVReader(new InputStreamReader(zis, "utf-8"));
        String line[] = null;
        while ((line = reader.readNext()) != null) {
//          System.out.println(StringUtils.join(line,"\t"));
          result.put(line[1], line[3]);
        }

      }
      for (int i = 0; i < freqModels2.size(); i++) {
        String tmp = result.getOrDefault(freqModels2.get(i), "0");
        resultLine[i] = tmp;
      }
      System.out.println(task);
      System.out.println(StringUtils.join(freqModels2, "\t"));
      System.out.println(StringUtils.join(resultLine, "\t"));


    } finally {
      IOUtils.closeQuietly(zis);
    }


  }


  public static void readAttr(String task, File file) throws Exception {

    Map<String, String> result = new HashMap<>();
    String resultLine[] = new String[11];
    ZipInputStream zis = null;
    try {
      zis = new ZipInputStream(new FileInputStream(file));

      ZipEntry entry = null;

      while ((entry = zis.getNextEntry()) != null) {
        if (freqModels.contains(entry.getName())) {
          CSVReader reader = new CSVReader(new InputStreamReader(zis, "utf-8"));
          String line[] = null;
          while ((line = reader.readNext()) != null) {
            if ("0".equals(line[1]) && "394".equals(line[3])) {
              result.put(entry.getName(), line[4]);
            }
          }
        }
      }
      for (int i = 0; i < freqModels.size(); i++) {
        String tmp = result.getOrDefault(freqModels.get(i), "0");
        resultLine[i] = tmp;
      }
      System.out.println(task);
      System.out.println(StringUtils.join(freqModels, "\t"));
      System.out.println(StringUtils.join(resultLine, "\t"));


    } finally {
      IOUtils.closeQuietly(zis);
    }

  }

}
