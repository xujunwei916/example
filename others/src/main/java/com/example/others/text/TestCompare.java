package com.example.others.text;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestCompare {


    public static void main(String[] args) throws Exception {

        Set<String> lines1 = new HashSet<>(IOUtils.readLines(new FileReader("d://tmp//tmp//1//mode1.txt")));
        Set<String> lines2 = new HashSet<>(IOUtils.readLines(new FileReader("d://tmp//tmp/3//mode1.txt")));
        List<String > result1 =new ArrayList<>();
        List<String > result2 =new ArrayList<>();
        System.out.println(lines1.size());
//        System.out.println(new HashSet<>(lines1).size());

        for (String line:lines1) {

            if(!lines2.contains(line)){
                result1.add(line);
            }
        }

        for (String line:lines2){
            if (!lines1.contains(line)){
                result2.add(line);
            }
        }
        System.out.println(result1.size());
        System.out.println(result1);
        System.out.println(result2.size());
        System.out.println(result2);



    }

}
