package com.example.others.ips;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class IPLocateDatx {
    private byte[] data;

    private int indexSize;
    private static Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");


    public void loadDat(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        data = Files.readAllBytes(path);

        indexSize = (int) (IPdataConvert.byte2long(data[0], data[1], data[2], data[3]));
    }


    public long ipToLong(String ip) {

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


    public String locateIp(String ip) {

        long nip = 0L;
        if (pattern.matcher(ip).matches()) {
            nip = ipToLong(ip);
        } else {
            return "ERROR IP";
        }


        int recordMin = 0;
        int recordMax = indexSize - 1;
        int recordMid = (recordMin + recordMax) / 2;

        while (recordMax - recordMin >= 0) {

            long minip = 0;

            if (recordMid > 0) {
                int pos1 = (recordMid - 1) * 9 + 4;
                minip = IPdataConvert.byte2long(data[pos1], data[pos1 + 1], data[pos1 + 2], data[pos1 + 3]) + 1;
            }


            int pos = recordMid * 9 + 4;
            long maxip = IPdataConvert.byte2long(data[pos], data[pos + 1], data[pos + 2], data[pos + 3]);
            if (nip < minip) {
                recordMax = recordMid - 1;
            } else if (nip == minip || nip > minip && nip < maxip || nip == maxip) {
                int addr = (int) (IPdataConvert.byte2long(data[pos + 4], data[pos + 5], data[pos + 6]));
                int len = (int) (IPdataConvert.byte2long(data[pos + 7], data[pos + 8]));
                byte[] resultByte = new byte[len];
                System.arraycopy(data, indexSize * 9 + 4+addr, resultByte, 0, len);
                return new String(resultByte, Charset.forName("utf8"));

            } else if (nip > maxip) {
                recordMin = recordMid + 1;
            } else {
                System.out.println("Error Case");
            }
            recordMid = (recordMin + recordMax) / 2;
        }


        return "Not Found";
    }

    public static void main(String[] args) throws Exception {
        IPLocateDatx ipLocate = new IPLocateDatx();
        ipLocate.loadDat("D://test//IP_trial_2018M06_single_WGS84.dat");
        String result = ipLocate.locateIp("1.204.54.213");
        System.out.println(result);
//        new IPLocate().loadDat("D://test//IP_trial_2018M06_single_WGS84.dat");
//      long result =   new IPLocate().ipToLong("1.204.54.213");
//        System.out.println(result);
    }

}
