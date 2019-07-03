package com.example.others.text;

import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CalaText {
    public static void main(String[] args) throws Exception {
        List<String> lines = IOUtils.readLines(new FileReader("C:\\Users\\xujw\\Downloads\\1010的单个次数_2019-02-01 10_50_45 AM.csv"));

        Map<String,Long> result = new TreeMap<>();
        for (int i = 1; i <lines.size() ; i++) {
            String line = lines.get(i);
            String fields[] = line.split(",");

            int count = Integer.valueOf(fields[2]);
            Long value = Long.valueOf(fields[3]);
            for (int j=1; j<=count;j++){
                String  key = fields[0]+","+fields[1]+","+String.format("%3d",j);
                Long tmp = result.get(key);
                if(tmp == null){
                    tmp=0L;
                }
                tmp+=value;
                result.put(key,tmp);
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\xujw\\Downloads\\1010的汇总次数_2019-02-01 10_50_45 AM.csv"));


        for (Map.Entry<String,Long> entry:result.entrySet()) {
            writer.write(entry.getKey()+","+entry.getValue());
            writer.newLine();
        }
        writer.close();

        
    }
}
