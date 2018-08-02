package com;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import org.bang50.union2.guess.log.parse.ParseLog2DF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import scala.collection.Map;

public class ImpressionInfo {
    public static void main(String[] args) throws Exception {
//        csvParse();


        String input = "D:\\test\\dsp_pv_20180717_result.csv";
//        CSVReader reader = new CSVReader();

    }

    public static void csvParse() throws IOException {
        String input ="D:\\test\\dsp_pv_20180717.csv";
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = null;

        String out ="D:\\test\\dsp_pv_20180717_result.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(out));


        while ((line = reader.readLine()) != null) {

            Map<String,String> map=ParseLog2DF.parseLog(line.replaceAll("\u0001","\t"));
            writer.writeNext(new String[]{map.get("bidid").get(),map.get("uid").get(),map.get("i").get(),map.get("q").get()});

        }
        reader.close();
        writer.close();
    }
}
