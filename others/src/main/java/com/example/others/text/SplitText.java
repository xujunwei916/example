package com.example.others.text;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitText {


    public static void main(String[] args) throws Exception {
        List<String > list = IOUtils.readLines(new FileReader("D://tmp//QS125151.csv"));
        Map<String,List<String>> result = new HashMap<>();

        for (String line : list) {
            String fields [] =line.split(",",-1);
            String key = fields[1]+"_"+fields[2];

            List<String> tmp =  result.get(key);
            if(tmp == null ){
                tmp = new ArrayList<>(1000);
                result.put(key,tmp);
            }
            tmp.add("\""+StringUtils.join(fields,"\",\"`")+"\"");
        }

        for (Map.Entry<String,List<String>> entry:result.entrySet()) {
            String fileName = "D://tmp//QS125151_"+entry.getKey()+".csv";
            FileWriter writer = new FileWriter(fileName);
            IOUtils.writeLines(Arrays.asList("dt,task_id,action,imei,pv"),null,writer);
            IOUtils.writeLines(entry.getValue(),null,writer);
            writer.close();

        }





    }



}
