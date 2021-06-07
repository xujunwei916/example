package com.example.others.text;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class LinearPathMedia2 {

  public static void main(String[] args) throws Exception {

    String placementFile = "D:\\jira\\jbl\\media.csv";
    String inputFile = "D:\\jira\\jbl\\hp_03_media_4.csv";
    String outputFile = "D:\\jira\\jbl\\hp_03_media_name_4.csv";
    Map<String, String> placementMap = new HashMap<>();

    CSVReader reader = new CSVReader(new FileReader(placementFile));

    String[] lines = null;

    boolean readHeader = true;
    while ((lines = reader.readNext()) != null) {

      if (readHeader) {
        readHeader = false;
      } else {
        String name = lines[1];
        String id = lines[0];
        placementMap.put(id, name);
      }
    }
    reader.close();

    CSVWriter writer = new CSVWriter(new FileWriter(outputFile));
    CSVReader readerInput = new CSVReader(new FileReader(inputFile));

    lines = null;
    readHeader = true;
    int count = 0;
    while ((lines = readerInput.readNext()) != null) {
      count++;
      if (readHeader) {
        readHeader = false;
        writer.writeNext(lines);

      } else {
        String path = lines[0];
        String nodes[] = path.split("\\|");

        StringBuffer buffer = new StringBuffer(100);
        boolean flag = false;
        for (String node : nodes) {
          if (flag) {
            buffer.append("|");
          } else {
            flag = true;
          }
//          System.out.println(node);
          buffer.append(node.substring(0, 2));
          String name = placementMap.get(node.substring(2));
          if (name == null) {
            System.out.println(path + " : " + count);
          } else {
            buffer.append(name);
          }
        }
        lines[0] = buffer.toString();
        writer.writeNext(lines);

      }
    }
    reader.close();
    writer.close();


  }

}
