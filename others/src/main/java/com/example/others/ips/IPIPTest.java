package com.example.others.ips;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class IPIPTest {
    public static Pattern IP_REGEX = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");

    public static void main(String[] args) throws Exception {
        List<String> ips = IOUtils.readLines(new FileInputStream("D://test//ips.txt"));
//        City city = new City("D://test//17monipdb.datx");
        IPLocate ipLocate = new IPLocate();
        ipLocate.loadDat("D://test//IP_trial_2018M06_single_WGS84.dat");
        IPLocateDatx ipLocateDatx = new IPLocateDatx();
        ipLocateDatx.loadDat("D://test//IP_trial_2018M06_single_WGS84.datx");

        for (String ip : ips) {
//            System.out.println(IP_REGEX.matcher(ip).matches());
//            String [] citys1 = city.find(ip);
            String[] citys1 = ipLocateDatx.locateIp(ip).split("\\|", -1);
            String[] citys2 = ipLocate.locateIp(ip).split("\\|", -1);

            if (citys1[2].equals(citys2[5]) &&citys1[1].equals(citys2[4]) &&citys1[0].equals(citys2[3])) {

            } else {
                System.out.println("----s--");
                System.out.println(ip);
                System.out.println(Arrays.toString(citys1));
                System.out.println(Arrays.toString(citys2));
                System.out.println("----e--");
            }

        }
//        init("D://test//17monipdb.datx");

    }

    public static String find(String ip) {
        if (StringUtils.isEmpty(ip) || !IP_REGEX.matcher(ip).matches()) {
            return "N/A";
        }
        String[] ipDot = ip.split("\\.");


        return null;
    }
//    public static void init (String dataPath) throws Exception {
//        FileInputStream fin = new FileInputStream(dataPath);
//        byte [] values = new byte[4];
//
//        int len = fin.read(values,0,4);
////        System.out.println(new String(values));
//        for (byte b:values             ) {
//            System.out.println(b);
//        }
//    }

}
