package com.example.others.text;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestCompareSubString {


    public static void main(String[] args) throws Exception {
        List<String> tmp1 = IOUtils.readLines(new FileReader("d://tmp//tmp//1//mode2.txt"));
        List<String> tmp2 = IOUtils.readLines(new FileReader("d://tmp//tmp/3//mode2.txt"));
        Set<String> lines1 = new HashSet<>();
        Set<String> lines2 = new HashSet<>();

        for (String line : tmp1) {
            String fields[] = line.split(",", 5);
            if (fields.length == 5) {

                lines1.add(StringUtils.join(new String[]{fields[0], fields[1], fields[2], fields[3]}, ","));
            } else {
                System.out.println(line);
            }
        }
        for (String line : tmp2) {
            String fields[] = line.split(",", 5);
            if (fields.length == 5) {
                lines2.add(StringUtils.join(new String[]{fields[0], fields[1], fields[2], fields[3]}, ","));
            } else {
                System.out.println(line);
            }
        }


        List<String> result1 = new ArrayList<>();
        List<String> result2 = new ArrayList<>();
        System.out.println(lines1.size());
//        System.out.println(new HashSet<>(lines1).size());

        for (String line : lines1) {

            if (!lines2.contains(line)) {
                result1.add(line);
            }
        }

        for (String line : lines2) {
            if (!lines1.contains(line)) {
                result2.add(line);
            }
        }
        System.out.println(result1.size());
        System.out.println(result1);
        System.out.println(result2.size());
        System.out.println(result2);


    }

}
