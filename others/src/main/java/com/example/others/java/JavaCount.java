package com.example.others.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class JavaCount {
    private static long count=0;
    private static BufferedReader br;

    public static void main(String[] args) throws Exception {
        File f=new File("E:\\workspace\\incubator-dolphinscheduler-1.1.0");
        go(f);
        System.out.println(count);
    }

    private static void go(File f) throws Exception {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files!=null) {
                for (File file : files) {
                    go(file);
                }
            }
        }else {
            if (f.getName().endsWith(".java")) {
                br=new BufferedReader(new FileReader(f));
                while (br.readLine()!=null) {
                    count++;
                }
            }
        }
    }

}
