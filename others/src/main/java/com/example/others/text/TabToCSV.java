package com.example.others.text;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TabToCSV {
    public static void main(String[] args) throws Exception {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}.*$");

        Pattern pattern2 = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})\t([a-zA-Z0-9_\\.]+)\t(.*)\t([\\-0-9\\.]*)\t(.*)\t(\\d+)$");

        String headers[] = new String[]{"date", "package", "appName", "version", "appTitle", "num"};
        BufferedReader reader = new BufferedReader(new FileReader("D:\\tmp\\QS\\QD.txt"));
        CSVWriter writer = new CSVWriter(new FileWriter("D:\\tmp\\QS\\result\\QD.txt"));
        String line = null;
        String tmpLine = null;
        writer.writeNext(headers);

        while ((line = reader.readLine()) != null) {
            if (pattern.matcher(line).matches()) {

                if (tmpLine == null) {
                    tmpLine = line;
                } else {

                    Matcher matcher = pattern2.matcher(tmpLine);

                    if (matcher.find()) {
                        String[] f = new String[]{matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6)};
                        writer.writeNext(f);

                    } else {
                        System.out.println(tmpLine);
                    }
                    tmpLine=line;
                }
            } else {
                tmpLine += line;
            }
        }

        Matcher matcher = pattern2.matcher(tmpLine);

        if (matcher.find()) {
            String[] f = new String[]{matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6)};
            writer.writeNext(f);

        } else {
            System.out.println(tmpLine);
        }
        reader.close();
        writer.close();

    }
}
