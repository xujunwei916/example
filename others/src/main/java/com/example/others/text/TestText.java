package com.example.others.text;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestText {
    public static void main(String[] args) throws Exception {
        List<String> line1 = new ArrayList<String>();
        line1.addAll(IOUtils.readLines(new FileReader("D:\\test\\yd\\order_2018-12-27_game.txt")));
        line1.addAll(IOUtils.readLines(new FileReader("D:\\test\\yd\\order_2018-12-27_lahuo.txt")));
        line1.addAll(IOUtils.readLines(new FileReader("D:\\test\\yd\\order_2018-12-27_news.txt")));

        List<String> line2 = IOUtils.readLines(new FileReader("D:\\test\\yd\\1227.log"));

        Set<String> orders = new HashSet<String>();

        Set<String > orders2= new HashSet<>();

        for (String line : line1) {
            if(StringUtils.isEmpty(line)){
                continue;
            }
            String fields[] = line.split(",");
//            System.out.println(fields[1]);
            orders.add(fields[1]);
        }

        Pattern pattern = Pattern.compile("\"order_id\":\"([^\"]*)\"");

        List<String> result = new ArrayList<>();
        for (String line : line2) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {

                String order = matcher.group(1);
                if(orders.contains(order)){

                    orders2.add(order);
                }else{
                    result.add(line);
                }
//                System.out.println(order);
            } else {
                System.out.println(line);
            }

        }
        for (String line : line1) {
            if(StringUtils.isEmpty(line)){
                continue;
            }
            String fields[] = line.split(",");
//            System.out.println(fields[1]);
//            orders.add(fields[1]);
            if(!orders2.contains(fields[1])){
                System.out.println(line);
            }
        }
        FileWriter fileWriter = new FileWriter("D:\\test\\yd\\result27.txt");
        IOUtils.writeLines(result,"\n",fileWriter);
        fileWriter.close();


    }
}
