package com.example.others.text;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestText3 {
    public static void main(String[] args) throws Exception {
        List<String> line1 = new ArrayList<String>();
        line1.addAll(IOUtils.readLines(new FileReader("D:\\test\\yd\\lost-order.txt")));

//        List<String> line2 = IOUtils.readLines(new FileReader("D:\\test\\yd\\2019_05_23_response_200.log"));
        BufferedReader reader = new BufferedReader(new FileReader("D:\\test\\yd\\2019_05_23_response_200.log"));
        Set<String> orders = new HashSet<String>();

        Set<String > orders2= new HashSet<>();

        for (String line : line1) {
            if(StringUtils.isEmpty(line)){
                continue;
            }
//            String fields[] = line.split(",");
////            System.out.println(fields[1]);
            orders.add(line);
        }

        Pattern pattern = Pattern.compile("\"order_id\":\"([^\"]*)\"");

        List<String> result = new ArrayList<>();

        String tmpline =null;
        while( (tmpline =reader.readLine()) !=null) {
            Matcher matcher = pattern.matcher(tmpline);
            if (matcher.find()) {

                String order = matcher.group(1);
                if(orders.contains(order)){

                    orders2.add(order);
                    result.add(tmpline);
                }else{

                }
            } else {
//                System.out.println(tmpline);
            }

        }
        for (String line : line1) {
            if(StringUtils.isEmpty(line)){
                continue;
            }
//            String fields[] = line.split(",");
//            System.out.println(fields[1]);
//            orders.add(fields[1]);
            if(!orders2.contains(line)){
                System.out.println(line);
            }
        }
        FileWriter fileWriter = new FileWriter("D:\\test\\yd\\result23.txt");
        IOUtils.writeLines(result,"\n",fileWriter);
        fileWriter.close();


    }
}
