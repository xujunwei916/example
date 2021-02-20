package com.example.others.text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json4s.FileInput;

public class TestCompare {


    public static void main(String[] args) throws Exception {

        List<String> lines1 =  IOUtils.readLines(new FileInputStream("D://2222.txt"));
        List<String> lines2 =  IOUtils.readLines(new FileInputStream("D://1111.txt"));
        Set<String> set = new HashSet<>(5000000);
        set.addAll(lines1);
        System.out.println(lines1.size());
        System.out.println(lines2.size());
        int count =0;
        for (String line :lines2) {
            if(!set.contains(line)){
                count++;
            }
        }
        System.out.println(count);

    }


}
