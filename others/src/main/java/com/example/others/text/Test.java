package com.example.others.text;

import com.google.common.collect.Lists;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

//        List<String> line1 =IOUtils.readLines(new FileReader("C:\\Users\\xujw\\Desktop\\tmp\\imei_t6.txt"));
//        List<String> line2 =IOUtils.readLines(new FileReader("C:\\Users\\xujw\\Desktop\\tmp\\imei_50bang.txt"));
//
//        List<String> result =ListUtils.removeAll(line2,line1);
//
//        FileWriter writer = new FileWriter("C:\\Users\\xujw\\Desktop\\tmp\\other.txt");
//        IOUtils.writeLines(result,null,writer);
//        writer.close();
//        Object s = 1;
//        System.out.println(s instanceof Long);
        DateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> lines = IOUtils.readLines(new FileReader("D://tmp/timestamp.txt"));
        lines.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String line: lines
             ) {
            System.out.println(SDF.format(new Date(Long.valueOf(line))) );

        }



    }
}
