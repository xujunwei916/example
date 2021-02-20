package com.example.others.text;

import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

public class TestCPOCompare {

  public static void main(String[] args) throws Exception {

    String selfFile = "D:\\test\\245_hspser46_brand_New_Existing\\245_hspser46_brand_New_Existing.csv";

    String autoFile = "D:\\test\\245_hspser46_brand_New_Existing\\part-00000.txt";
    List<String> self = IOUtils.readLines(new FileReader(selfFile));

    List<String> auto = IOUtils.readLines(new FileReader(autoFile));

    Set<String> selfSet = new HashSet<>();
    for (String line : self) {
      selfSet.add(line.split("\t")[2]);
    }

   Pattern pattern =  Pattern.compile("TRADE_NO\\u0003(\\d+)");

    Set<String> autoSet = new HashSet<>();
    for (String line : auto) {

      Matcher matcher = pattern.matcher(line);
      if(matcher.find()){
        autoSet.add(matcher.group(1));
      }
    }
    System.out.println(autoSet.size());
    System.out.println(selfSet.size());

//    System.out.println(autoSet.removeAll(selfSet));
//    System.out.println(autoSet.size());
//    System.out.println(selfSet.size());
//    System.out.println(autoSet);

    System.out.println(selfSet.removeAll(autoSet));
    System.out.println(autoSet.size());
    System.out.println(selfSet.size());


  }

}
