package com.example.hive.udf;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPdataConvert {
    public static void main(String[] args) throws Exception {
        convert("D://test//IP_trial_2018M06_single_WGS84.txt", "D://test//IP_trial_2018M06_single_WGS84.datx");
    }

    /**
     * 转化mysql的数据为datx数据进行压缩存储
     * 只存放maxIP
     * @param input 输入文件路径
     * @param output 输出文件出
     * @throws Exception 转换失败异常
     */
    public static void convert(String input, String output) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line = null;
        List<IP> ips = new ArrayList<IP>();
        line = reader.readLine();
        if (line == null) {
            throw new RuntimeException("文件为空");
        }
        IP lastIP = null;
        int index = 0;
        Map<String, Integer> keys = new HashMap<>();
        int count = 0;
        while ((line = reader.readLine()) != null) {
            count++;
            if (count % 1000000 == 0) {
                System.out.println(count);
            }

            String fields[] = line.split("\t", -1);
            if (fields.length != 18) {
                System.out.println(line);
                throw new RuntimeException("文件内容错误");
            }
            long minIP = Long.valueOf(trimStr(fields[1], "\""));
            long maxIP = Long.valueOf(trimStr(fields[2], "\""));
            String country = trimStr(fields[6], "\"");
            String province = trimStr(fields[7], "\"");
            String city = trimStr(fields[8], "\"");
            String key = country + "|" + province + "|" + city;
            Integer currentIndex = keys.get(key);
            if (currentIndex == null) {
                currentIndex = index;
                index++;
                keys.put(key, currentIndex);
            }
            IP ip = new IP(minIP, maxIP, currentIndex);
            if (lastIP == null) {
                lastIP = ip;
            } else {
                if (lastIP.getMaxIp() + 1 != ip.getMinIP()) {
                    System.out.println("不连续 " + line);
                }
                if (lastIP.getCode() != ip.getCode() || lastIP.getMaxIp() + 1 != ip.getMinIP()) {
                    ips.add(lastIP);
                    lastIP = ip;
                } else {
                    lastIP.setMaxIp(ip.getMaxIp());
                }
            }

        }
        if (lastIP != null) {
            ips.add(lastIP);
        }
        reader.close();


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Map<Integer, Index> mapIndexs = new HashMap<Integer, Index>();
        int totalLen = 0;
        for (Map.Entry<String, Integer> entry : keys.entrySet()) {
            byte[] bytes = entry.getKey().getBytes("utf8");
            byteArrayOutputStream.write(bytes);
            mapIndexs.put(entry.getValue(), new Index(totalLen, bytes.length));
            totalLen += bytes.length;
        }
        byte[] keyBytes = byteArrayOutputStream.toByteArray();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();


        for (IP ip : ips) {
           byte [] maxIpByte =  long2byte(ip.getMaxIp(),4);
           Index keyIndex  = mapIndexs.get(ip.getCode());
           if(keyIndex==null){
               throw new RuntimeException("转换错误");
           }
           byte [] addrByte = long2byte(keyIndex.getStart(),3);
           byte [] addrLenByte = long2byte(keyIndex.getLen(),2);
            byteArrayOutputStream2.write(maxIpByte);
            byteArrayOutputStream2.write(addrByte);
            byteArrayOutputStream2.write(addrLenByte);
        }
        byte [] maxIPBytes= byteArrayOutputStream2.toByteArray();


        FileOutputStream fileOutputStream  =  new FileOutputStream(output);

        fileOutputStream.write(long2byte(ips.size(),4));
        fileOutputStream.write(maxIPBytes);
        fileOutputStream.write(keyBytes);
        fileOutputStream.close();

    }

    /**
     * 去掉前后字符串
     */
    public static String trimStr(String value, String str) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        if (value.equals(str)) {
            return "";
        }
        if (value.startsWith(str)) {
            if (value.endsWith(str)) {
                return value.substring(str.length(), value.length() - str.length());
            } else {
                return value.substring(str.length());
            }
        } else {
            if (value.endsWith(str)) {
                return value.substring(0, value.length() - str.length());
            } else {
                return value;
            }
        }
    }

    public static byte[] long2byte(long value, int len) {
        if (len > 0 && len <= 8) {

            byte[] result = new byte[len];

            for (int i = 0; i < len; i++) {
                result[i] = (byte) ((value >> i * 8) & 0xff);
            }
            return result;

        } else {
            throw new RuntimeException("转换long失败");
        }
    }
    public static long byte2long(byte ... value) {
        if (value.length > 0 && value.length <= 8) {

            long l =0L;

            for (int i = 0; i < value.length; i++) {
                l |= (value[i] & 0xffL) << i * 8;
            }
            return l;

        } else {
            throw new RuntimeException("转换long失败");
        }
    }
}

class IP {
    private long minIP;
    private long maxIp;
    private int code;


    public IP(long minIP, long maxIp) {
        this.minIP = minIP;
        this.maxIp = maxIp;
    }

    public IP(long minIP, long maxIp, int code) {
        this.minIP = minIP;
        this.maxIp = maxIp;
        this.code = code;
    }


    public IP() {
    }

    public long getMinIP() {
        return minIP;
    }

    public void setMinIP(long minIP) {
        this.minIP = minIP;
    }

    public long getMaxIp() {
        return maxIp;
    }

    public void setMaxIp(long maxIp) {
        this.maxIp = maxIp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "IP{" +
                "minIP=" + minIP +
                ", maxIp=" + maxIp +
                ", code=" + code +
                '}';
    }
}

class Index {
    private int start;
    private int len;

    public Index() {
    }

    public Index(int start, int len) {
        this.start = start;
        this.len = len;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
