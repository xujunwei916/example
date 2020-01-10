package com.example.others.test;

import au.com.bytecode.opencsv.CSVWriter;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;
import org.nlpcn.commons.lang.util.IOUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestA {
    private String a;

    public static void main(String[] args) throws Exception {
//        List<String> lines1 = IOUtils.readLines(new FileReader("D:\\tmp\\test1.txt"));
//        List<String> lines2 = IOUtils.readLines(new FileReader("D:\\tmp\\test2.txt"));
//        List<String> lines3 = IOUtils.readLines(new FileReader("D:\\tmp\\test3.txt"));
//
//        System.out.println(lines1.retainAll(lines3));
//        System.out.println(lines1);
//        List<String> lines4 = IOUtils.readLines(new FileReader("D:\\tmp\\test4.txt"));
//        Set<String> set = new HashSet<>();
//        for (String l : lines4
//             ) {
//            if(set.contains(l.trim())){
//                System.out.println(l);
//                set.add(l.trim());
//            }
//
//        }

        CSVWriter writer = new CSVWriter(new FileWriter("D://test.csv"),',',CSVWriter.NO_QUOTE_CHARACTER);

        writer.writeNext(new String[]{"ss\n\"d,jj","0","0.0"});
        writer.close();

    }
}
