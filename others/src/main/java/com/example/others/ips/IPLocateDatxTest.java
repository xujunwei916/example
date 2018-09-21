package com.example.others.ips;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class IPLocateDatxTest {
    private byte[] data;

    private long defaultOffsetAddr;
    private long defaultOffsetOwner;
    private int BASE_LEN = 64;
    private static Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");


    public void loadDat(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        data = Files.readAllBytes(path);
        defaultOffsetAddr = IPdataConvert.byte2long(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
        defaultOffsetOwner = IPdataConvert.byte2long(data[8], data[9], data[10], data[11], data[12], data[13], data[14], data[15]);

        System.out.println(defaultOffsetAddr);
        System.out.println(defaultOffsetOwner);
    }

    public void writerFile(String file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            int records = (int) defaultOffsetAddr / BASE_LEN;
            System.out.println(records);

            for (int i = 0; i < records; i++) {
                int pos = i * BASE_LEN + 16;
                long minIP = IPdataConvert.byte2long(data[pos + 0], data[pos + 1], data[pos + 2], data[pos + 3]);
                long maxIP = IPdataConvert.byte2long(data[pos + 4], data[pos + 5], data[pos + 6], data[pos + 7]);
//                long minIP = IPdataConvert.byte2long(data[pos + 3], data[pos + 2], data[pos + 1], data[pos + 0]);
//                long maxIP = IPdataConvert.byte2long(data[pos + 7], data[pos + 6], data[pos + 5], data[pos + 4]);

                int addr_begin = (int) IPdataConvert.byte2long(data[pos + 8], data[pos + 9], data[pos + 10], data[pos + 11], data[pos + 12], data[pos + 13], data[pos + 14], data[pos + 15]);
                int addr_len = (int) IPdataConvert.byte2long(data[pos + 16], data[pos + 17], data[pos + 18], data[pos + 19], data[pos + 20], data[pos + 21], data[pos + 22], data[pos + 23]);
                int owner_begin = (int) IPdataConvert.byte2long(data[pos + 24], data[pos + 25], data[pos + 26], data[pos + 27], data[pos + 28], data[pos + 29], data[pos + 30], data[pos + 31]);
                int owner_len = (int) IPdataConvert.byte2long(data[pos + 32], data[pos + 33], data[pos + 34], data[pos + 35], data[pos + 36], data[pos + 37], data[pos + 38], data[pos + 39]);

                byte[] addr_bundle = new byte[addr_len];
                System.arraycopy(data, addr_begin + 16, addr_bundle, 0, addr_len);
                String addr_bundle_str = new String(addr_bundle, "utf-8");

                byte[] owner = new byte[owner_len];
                System.arraycopy(data, owner_begin + 16, owner, 0, owner_len);
                String owner_str = new String(owner, "utf-8");
                writer.write(StringUtils.join(new Object[]{minIP,maxIP,addr_bundle_str,owner_str},","));
                writer.newLine();

            }
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

//
//    public long ipToLong(String ip) {
//
//        String ipDot[] = ip.split("\\.");
//        if (ipDot.length != 4) {
//            throw new RuntimeException("ip is ERROR");
//        } else {
//            long result = 0l;
//            for (int i = 0; i < ipDot.length; i++) {
//                result |= (Integer.valueOf(ipDot[i]) & 0xffL) << (ipDot.length - 1 - i) * 8;
//            }
//            return result;
//
//        }
//
//    }
//
//
//    public String locateIp(String ip) {
//
//        long nip = 0L;
//        if (pattern.matcher(ip).matches()) {
//            nip = ipToLong(ip);
//        } else {
//            return "ERROR IP";
//        }
//
//
//        int recordMin = 0;
//        int recordMax = indexSize - 1;
//        int recordMid = (recordMin + recordMax) / 2;
//
//        while (recordMax - recordMin >= 0) {
//
//            long minip = 0;
//
//            if (recordMid > 0) {
//                int pos1 = (recordMid - 1) * 9 + 4;
//                minip = IPdataConvert.byte2long(data[pos1], data[pos1 + 1], data[pos1 + 2], data[pos1 + 3]) + 1;
//            }
//
//
//            int pos = recordMid * 9 + 4;
//            long maxip = IPdataConvert.byte2long(data[pos], data[pos + 1], data[pos + 2], data[pos + 3]);
//            if (nip < minip) {
//                recordMax = recordMid - 1;
//            } else if (nip == minip || nip > minip && nip < maxip || nip == maxip) {
//                int addr = (int) (IPdataConvert.byte2long(data[pos + 4], data[pos + 5], data[pos + 6]));
//                int len = (int) (IPdataConvert.byte2long(data[pos + 7], data[pos + 8]));
//                byte[] resultByte = new byte[len];
//                System.arraycopy(data, indexSize * 9 + 4+addr, resultByte, 0, len);
//                return new String(resultByte, Charset.forName("utf8"));
//
//            } else if (nip > maxip) {
//                recordMin = recordMid + 1;
//            } else {
//                System.out.println("Error Case");
//            }
//            recordMid = (recordMin + recordMax) / 2;
//        }
//
//
//        return "Not Found";
//    }

    public static void main(String[] args) throws Exception {
        IPLocateDatxTest ipLocate = new IPLocateDatxTest();
        ipLocate.loadDat("F:\\知识\\IP_basic_2018W33_single_WGS84_simple_dat\\IP_basic_2018W33_single_WGS84_simple.dat");
        ipLocate.writerFile("F:\\知识\\IP_basic_2018W33_single_WGS84_simple_dat\\IP_basic_2018W33_single_WGS84_simple.txt");
//        String result = ipLocate.locateIp("1.204.54.213");
//        System.out.println(result);
////        new IPLocate().loadDat("D://test//IP_trial_2018M06_single_WGS84.dat");
//      long result =   new IPLocate().ipToLong("1.204.54.213");
//        System.out.println(result);
    }

}
