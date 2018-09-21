package com.example.others.text;

import com.example.others.ips.IPLocateDatx;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ip_date {


    public static void main(String[] args) throws Exception {
        IPLocateDatx ipLocateDatx =  new IPLocateDatx();
        ipLocateDatx.loadDat("D:\\tmp\\IP_trial_single_WGS84.datx");


//
      List<String> lines =   IOUtils.readLines(new FileReader("D:\\tmp\\ip_data.txt"));

      Set<String> heilongjiang = new HashSet<>();
        Set<String> jilin = new HashSet<>();
        Set<String> liaoning = new HashSet<>();

        for (String line: lines ) {
            String fields [] = line.split(",");
            String imei =fields[0];
            String ip = fields[1];
            String[] geo = ipLocateDatx.locateIp(ip).split("\\|", -1);
            String province = geo.length != 3? "N/A":geo[1];
//            System.out.println(province);
            if("黑龙江省".equals(province)){
                heilongjiang.add(imei);
            }
            if("吉林省".equals(province)){
                jilin.add(imei);
            }

            if("辽宁省".equals(province)){
                liaoning.add(imei);
            }


        }
        System.out.println(jilin.size());
        System.out.println(heilongjiang.size());
        System.out.println(liaoning.size());



    }
}
