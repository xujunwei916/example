package com.example.others.text;


import org.apache.commons.io.IOUtils;
import org.bang50.union2.guess.tools.UrlTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;


public class TestDomainText {
    public static Pattern  preg = Pattern.compile( "^[a-zA-Z]+:");
    public static void main(String[] args) throws Exception {
//        List<String> lines = IOUtils.readLines(new FileReader("d:\\test\\08.txt"));
//        BufferedReader reader = new BufferedReader(new FileReader("d:\\test\\08.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("d:\\test\\08.txt.gz")),"utf8"));
        int count =0;
        String line =null;
        while ((line=reader.readLine())!=null) {
            if(count%10000==0){
                System.out.println(count);
            }
            count++;
            String [] ary = line.split("\t");
            if (ary.length >= 4 && ary[3].length()> 0)
            {
                String lo =  (!preg.matcher(ary[3]).find()) ?UrlTools.urlDecode(ary[3].toLowerCase()) : ary[3].toLowerCase();
                if("2".equals(lo)){
                    System.out.println(line);
                }
            }
        }
        reader.close();
        
    }
}
