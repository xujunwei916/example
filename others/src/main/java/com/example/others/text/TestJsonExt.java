package com.example.others.text;


import com.example.others.gson.JsonHelper;

import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestJsonExt {
    public static void main(String[] args) throws Exception {
        List<String> line1 = IOUtils.readLines(new FileReader("D:\\test\\yd\\2018_12_21_response_error.log"));
        List<String> result = new ArrayList<>(1000000);
        Set<String> orderids = new HashSet<>(100000);


        for (String line: line1) {
                String tmp[] = line.split("\t");
                String time = tmp[0];
            Map<String,Object> jsonMap = JsonHelper.json2Object(tmp[4],Map.class);

            Map<String,Object> requestData = (Map<String,Object> ) jsonMap.get("requestData");

            String data = requestData.get("data").toString();
            List<Object> dataArray = JsonHelper.json2Object(data,List.class);
            for (Object o:dataArray) {
                String orderId= (String)((Map<String,Object> )o).get("order_id");
                if(!orderids.contains(orderId)){
                    result.add(time+"\t"+JsonHelper.toJson(o));
                    orderids.add(orderId);
                }

                System.out.println(orderId);
            }
//            result.add(time+"\t"+data);

        }
        FileWriter writer = new FileWriter("D:\\test\\yd\\2018_12_21_response_result.txt");

        IOUtils.writeLines(result,null,writer);
        writer.close();



    }
}
