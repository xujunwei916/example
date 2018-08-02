package com.example.others.ips;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class IPRange {
    public static Long[] getIpRange(String ip) {

        String fields[] = ip.split("/");
        if (fields == null || fields.length != 2) {
            return null;
        }
        long min = ipToLong(fields[0]);

        long max = min | (0xffffffffL >> Integer.valueOf(fields[1]));


        return new Long[]{min, max};

    }

    public static long ipToLong(String ip) {

        String ipDot[] = ip.split("\\.");
        if (ipDot.length != 4) {
            throw new RuntimeException("ip is ERROR");
        } else {
            long result = 0l;
            for (int i = 0; i < ipDot.length; i++) {
                result |= (Integer.valueOf(ipDot[i]) & 0xffL) << (ipDot.length - 1 - i) * 8;
            }
            return result;

        }

    }

    public static void main(String[] args) throws Exception {
        List<String> lines = IOUtils.readLines(new FileReader("D://test/network2.txt"));
        List<String> result = new ArrayList<>();
        long lastMax = 0;
        int count = 0;
        for (String line : lines) {
            count++;
            String fields[] = line.split(",");
            if (Long.valueOf(fields[2]) <= lastMax) {
                System.out.println(line);
                System.out.println(count);
            }
        }


    }
}
