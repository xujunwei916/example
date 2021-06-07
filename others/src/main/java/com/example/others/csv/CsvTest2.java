package com.example.others.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import java.io.FileReader;
import java.io.FileWriter;

public class CsvTest2 {

    public static void main(String[] args) throws Exception {

        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();
        CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(new FileReader("D://test/test_in.csv")).withCSVParser(rfc4180Parser);
//        CSVReader reader = new CSVReader(new FileReader("D://test/test_in.csv"));
        CSVReader reader = csvReaderBuilder.build();
        String out = "D://test//test.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(out));

        String [] lines = null;
        while ((lines=reader.readNext())!=null){
            writer.writeNext(lines);
        }
        reader.close();
        writer.close();

    }



}
