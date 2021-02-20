package com.example.others.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;

public class JavaCount {
    private static long count=0;
    private static BufferedReader br;

    public static void main(String[] args) throws Exception {
//        File f=new File("E:\\workspace\\incubator-dolphinscheduler-1.1.0");
//        go(f);
//        System.out.println(count);

        long i = -853564213l;
        long a = 0xffffffffl;


        long j =4294967296l;

        System.out.println(i&a);
        System.out.println(0l+  Integer.MAX_VALUE  -  Integer.MIN_VALUE+1);

        System.out.println((int)j);
//        BigDecimal b1 = new BigDecimal(0);
//        System.out.println(b1.equals(BigDecimal.ZERO));
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
