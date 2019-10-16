package com.example.others.text;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class TestTextUnicode {


    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test\\test\\shoujilm_oanew_technician-2019-06-24.txt"),"utf8"));


        String line = null;

        while ((line = reader.readLine()) != null) {
//            System.out.println(line);


            System.out.println(new String(line.getBytes(Charset.forName("ISO-8859-1")),"gbk"));
        }

        reader.close();
//        System.out.println(new String("这就是觉得".getBytes("utf8"),"ISO-8859-1"));
//        System.out.println(new String ("µçÄÔ³Ç".getBytes("ISO-8859-1"),"gbk"));
    }
}
