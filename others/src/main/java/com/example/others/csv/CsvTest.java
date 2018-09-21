package com.example.others.csv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvTest {

    public static void main(String[] args) throws Exception {
//        csvDate("D:\\test\\InstalledApp_2018_result_3.csv","D:\\test\\InstalledApp_2018_result_4.csv");
//        csvLine("D:\\test\\InstalledApp_2018-07-02_2018-07-10.csv","D:\\test\\InstalledApp_2018_result_1.csv");
//        csvS("D:\\test\\InstalledApp_2018_result_1.csv","D:\\test\\InstalledApp_2018_result");
        csvDate("D:\\test\\InstalledApp_2018_resultSY.csv","D:\\test\\InstalledApp_2018_resultSY_1.csv");
        csvDate("D:\\test\\InstalledApp_2018_resultSZ.csv","D:\\test\\InstalledApp_2018_resultSZ_1.csv");
        csvDate("D:\\test\\InstalledApp_2018_resultTQ.csv","D:\\test\\InstalledApp_2018_resultTQ_1.csv");

    }


    public static void csvS(String input,String out) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = null;

        BufferedWriter writerSY = new BufferedWriter(new FileWriter(out+"SY.csv"));
        BufferedWriter writerSZ = new BufferedWriter(new FileWriter(out+"SZ.csv"));
        BufferedWriter writerTQ = new BufferedWriter(new FileWriter(out+"TQ.csv"));


        while ((line = reader.readLine()) != null) {
            if(line.startsWith("SY")){
                writerSY.write(line.substring(3));
                writerSY.newLine();
            }else if(line.startsWith("SZ")){
                writerSZ.write(line.substring(3));
                writerSZ.newLine();
            }else if(line.startsWith("TQ")){
                writerTQ.write(line.substring(3));
                writerTQ.newLine();
            }else{
                System.out.println(line+"|");
            }
        }
        reader.close();
        writerSY.close();
        writerSZ.close();
        writerTQ.close();
    }


    public static void csvLine(String input,String out) throws IOException {
        Pattern pattern = Pattern.compile("^((SY)|(SZ)|(TQ)),\\d{4}-\\d{2}-\\d{2}.*$");

        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = null;

        BufferedWriter writer = new BufferedWriter(new FileWriter(out));


        while ((line = reader.readLine()) != null) {
            if(pattern.matcher(line).matches()){
                writer.newLine();
                writer.write(line);

            }else{
                writer.write(line);
                System.out.println(line);
            }
        }
        reader.close();
        writer.close();
    }


    public static void csvDate(String input,String out) throws IOException {
        Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}),([a-zA-Z0-9_\\.]+),(.+),([0-9\\.]+),(.*),(\\d+)$");

        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = null;

        CSVWriter writer = new CSVWriter(new FileWriter(out));


        Set<String> packages = new HashSet<>();
        packages.addAll(Arrays.asList(new String[]{"com.daohang2345", "com.daohangforxiuzhuo",
                "com.browser_llqhz", "com.browser.oem", "com.android.browser",
                "com.browser2345_oem", "com.market2345.dingzhi", "com.market2345",
                "com.market.chenxiang", "com.androidmarket.dingzhi",
                "com.tianqiyubao2345", "com.tianqi2345"}));

        Set<String> dates = new HashSet<>();
        dates.addAll(Arrays.asList(new String[]{"2018-07-01",
                "2018-07-02",
                "2018-07-03",
                "2018-07-04",
                "2018-07-05",
                "2018-07-06",
                "2018-07-07",
                "2018-07-08",
                "2018-07-09"}));
        String headers[] = new String[]{"date", "package", "appName", "version", "appTitle", "num"};
        writer.writeNext(headers);
        while ((line = reader.readLine()) != null) {
            String [] fields = line.split(",");
            if (fields.length==6 ) {
                if(dates.contains(fields[0])){
                    writer.writeNext(fields);
                }

            } else if(pattern.matcher(line).matches()){
                try {
                    Matcher matcher = pattern.matcher(line);
                    matcher.find();
                    String [] f = new String []{matcher.group(1),matcher.group(2),matcher.group(3),matcher.group(4),matcher.group(5),matcher.group(6)};
                    if (dates.contains(f[0])) {
                        writer.writeNext(f);
                    }
                } catch (Exception e) {
                    System.out.println(line);
                }
            }else{
                System.out.println(line+"|");
            }
        }
        reader.close();
        writer.close();
    }
}
