package com.example.others.csv;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CsvTest2 {

    public static void main(String[] args) throws Exception {

        CSVReader reader = new CSVReader(new FileReader("D://test/test_in.csv"),',','"', CSVWriter.NO_ESCAPE_CHARACTER);

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
