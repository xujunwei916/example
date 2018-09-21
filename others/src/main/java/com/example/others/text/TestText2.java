package com.example.others.text;


import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestText2 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\test\\yd\\TEST.txt"));
        String line = null;
        Pattern pattern = Pattern.compile("\"brand\":\"([^\"]*)\"");
        Pattern pattern2 = Pattern.compile("\"model\":\"([^\"]*)\"");
        Map<String, Integer> result = new HashMap<>();
        int count=0;
        while ((line = reader.readLine()) != null) {

            Matcher matcher = pattern.matcher(line);
            Matcher matcher2 = pattern2.matcher(line);
            if (matcher.find() && matcher2.find()) {
                String brand = matcher.group(1);
                String model =matcher2.group(1);
                Integer tmp = result.getOrDefault(brand+"_______"+model, 0);
                result.put(brand+"_______"+model, tmp + 1);
            }else{
                count++;
//                System.out.println(line);
            }

        }
        System.out.println(count);
        reader.close();
        Map<Integer,String> map = new TreeMap<>();

        for (Map.Entry<String,Integer> o : result.entrySet()) {
//            if(o.getValue()>1000){
                System.out.println(o.getKey()+","+o.getValue());
//            }
//            map.put(0-o.getValue(),o.getKey());

        }
//        for (Map.Entry<Integer,String> o : map.entrySet()) {
//////            if(o.getValue()>1000){
//////                System.out.println(o);
//////            }
////            map.put(o.getValue(),o.getKey());
////            System.out.println(o.getValue()+"="+(0-o.getKey()));
//
//        }
//        System.out.println(decodeUnicode("\\u8363\\u8000\\u4f8d\\u536b"));

    }

    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}
