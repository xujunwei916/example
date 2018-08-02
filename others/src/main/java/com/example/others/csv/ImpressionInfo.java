package com.example.others.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ImpressionInfo {
    public static void main(String[] args) throws Exception {
        String input ="D:\\test\\dsp_pv_20180717";
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = null;

        String out ="D:\\test\\dsp_pv_20180717_result.csv";
        BufferedWriter writer = new BufferedWriter(new FileWriter(out));


        while ((line = reader.readLine()) != null) {
            String fields[] =line.split("\u0001",-1);

            String content= fields[2];

        }
        reader.close();
    }
}
