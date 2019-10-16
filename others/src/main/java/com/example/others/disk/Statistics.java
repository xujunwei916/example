package com.example.others.disk;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statistics {

    public static final Pattern pattern1 = Pattern.compile("([^ ]+)\\s+([^ ]+)\\s+/ods/(.*)/(\\d+)");
    public static final Pattern pattern2 = Pattern.compile("([0-9\\.]+ [KMGT])\\s+([0-9\\.]+ [KMGT])\\s+/ods/(.*)/(\\d+)");

//    public static final Pattern pattern3 = Pattern.compile("([0-9\\.]+ [KMGT])\\s+([0-9\\.]+ [KMGT])\\s+/user/hive/warehouse/dw_db.db/dw_(product_browser)_(.*)/p_dt=2019-09-23");


    public static void main(String[] args) throws Exception {

//        odsStatistics();
//        odsStatisticsMatch();
        String product = "safe";
        dw20190923(product);
        dwall(product);
    }

    public static void odsStatistics() throws Exception {
        List<String> lines = IOUtils.readLines(new FileReader(new File("D:\\test\\ods\\ods.txt")));
        List<String> noMatch = new ArrayList<>();
        List<String> matches = new ArrayList<>();
        int i = 0;
        for (String line : lines) {

            i++;
            Matcher matcher = pattern1.matcher(line);
            if (matcher.find()) {
                String size = matcher.group(1);
                String size2 = matcher.group(2);
//               String table = matcher.group(3);
//               String date = matcher.group(4);
                if ("0".equalsIgnoreCase(size) && "0".equalsIgnoreCase(size2)) {
                    System.out.println(line);
                } else {
                    matches.add(line);
                }
            } else {
                noMatch.add(line);
            }
        }

        FileWriter writer1 = new FileWriter("D:\\test\\ods\\ods_no_match.txt");
        IOUtils.writeLines(noMatch, null, writer1);
        writer1.close();
        FileWriter writer2 = new FileWriter("D:\\test\\ods\\ods_matches.txt");

        IOUtils.writeLines(matches, null, writer2);
        writer2.close();
    }


    public static void odsStatisticsMatch() throws Exception {
        List<String> lines = IOUtils.readLines(new FileReader(new File("D:\\test\\ods\\ods_matches.txt")));


        Map<String, SizeCount> result = new HashMap<>();
        for (String line : lines) {

            Matcher matcher = pattern2.matcher(line);
            if (matcher.find()) {
                String size = matcher.group(1);
                String size2 = matcher.group(2);
                String table = matcher.group(3);
                String date = matcher.group(4);


                SizeCount tmp = result.get(table);
                if (tmp == null) {
                    result.put(table, new SizeCount(sizeM(size), 1));
                } else {
                    tmp.add(sizeM(size),1);
                    result.put(table,tmp );
                }
            } else {
                System.out.println(line);
            }
        }
//        System.out.println(result);
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, SizeCount> entry : result.entrySet()) {
            res.add(entry.getKey()+ "\t"+entry.getValue().count  + "\t" + m1((double) (entry.getValue().size) / 1024 / 1024 / 1024,2));
        }

        FileWriter writer1 = new FileWriter("D:\\test\\ods\\ods_table_size.txt");
        IOUtils.writeLines(res, null, writer1);
        writer1.close();
    }


    public static void dw20190923(String projectName) throws Exception {
        List<String> lines = IOUtils.readLines(new FileReader(new File("D:\\test\\ods\\dw_20190923.txt")));

        Pattern pattern3 = Pattern.compile("([0-9\\.]+ [KMGT])\\s+([0-9\\.]+ [KMGT])\\s+/user/hive/warehouse/dw_db.db/dw_("+projectName+")_(.*)/p_dt=2019-09-23");
        Map<String, SizeCount> result = new HashMap<>();
        for (String line : lines) {
            Matcher matcher = pattern3.matcher(line);
            if (matcher.find()) {
                String size = matcher.group(1);
                String size2 = matcher.group(2);
                String product = matcher.group(3);
                String table = matcher.group(4);
                SizeCount tmp = result.get(product);
                if (tmp == null) {
                    result.put(product, new SizeCount(sizeM(size), 1));
                } else {
                    tmp.add(sizeM(size),1);
                    result.put(product,tmp );
                }
            } else {
                System.out.println(line);
            }
        }
//        System.out.println(result);
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, SizeCount> entry : result.entrySet()) {
            res.add(entry.getKey()+ "\t"+entry.getValue().count  + "\t" + m1((double) (entry.getValue().size) / 1024 / 1024 / 1024,2));
        }

        FileWriter writer1 = new FileWriter("D:\\test\\ods\\dw_table_size.txt");
        IOUtils.writeLines(res, null, writer1);
        writer1.close();
    }



    public static void dwall(String projectName) throws Exception {
        List<String> lines = IOUtils.readLines(new FileReader(new File("D:\\test\\ods\\dw_all.txt")));

        Pattern pattern3 = Pattern.compile("([0-9\\.]+ [KMGT])\\s+([0-9\\.]+ [KMGT])\\s+/user/hive/warehouse/dw_db.db/dw_("+projectName+")_(.*)");
        Map<String, SizeCount> result = new HashMap<>();
        for (String line : lines) {
            Matcher matcher = pattern3.matcher(line);
            if (matcher.find()) {
                String size = matcher.group(1);
                String size2 = matcher.group(2);
                String product = matcher.group(3);
                String table = matcher.group(4);
                SizeCount tmp = result.get(product);
                if (tmp == null) {
                    result.put(product, new SizeCount(sizeM(size), 1));
                } else {
                    tmp.add(sizeM(size),1);
                    result.put(product,tmp );
                }
            } else {
                System.out.println(line);
            }
        }
//        System.out.println(result);
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, SizeCount> entry : result.entrySet()) {
            res.add(entry.getKey()+ "\t"+entry.getValue().count  + "\t" + m1((double) (entry.getValue().size) / 1024 / 1024 / 1024,2));
        }

        FileWriter writer1 = new FileWriter("D:\\test\\ods\\dw_all_table_size.txt");
        IOUtils.writeLines(res, null, writer1);
        writer1.close();
    }
    public static long sizeM(String value) {

        String[] f = value.split("\\s+");
        if (f.length != 2) {
            throw new RuntimeException("ssss");
        }
        double tmp = Double.valueOf(f[0]);
        switch (f[1]) {
            case "K":
                return (long) (tmp * 1024L);
            case "M":
                return (long) (tmp * 1024L * 1024L);
            case "G":
                return (long) (tmp * 1024L * 1024L * 1024L);
            case "T":
                return (long) (tmp * 1024L * 1024L * 1024L * 1024L);
            default:
                return 0L;
        }

    }
    public static double m1(double value,int l) {
        BigDecimal bg = new BigDecimal(value);
        double f1 = bg.setScale(l, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(f1);
        return f1;
    }



}

class SizeCount {
    long size;
    int count;

    public SizeCount(long size, int count) {
        this.size = size;
        this.count = count;
    }

    public void add(long s, int c) {
        size += s;
        count += c;
    }
}

