package com.example.others.ips;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

public class IPLocate {
    private byte[] data;
    private int baseLen = 124;
    private long offsetAddr = 0;
    private long offsetOwner = 0;
    private byte[] offsetInfo = null;
    private static Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");


    public void loadDat(String fileName) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        byte[] tmp = new byte[8];
        fileInputStream.read(tmp, 0, 8);
        offsetAddr = bytesToLong(tmp);
        fileInputStream.read(tmp, 0, 8);
        offsetOwner = bytesToLong(tmp);
        int remainLen = fileInputStream.available();
        if (remainLen > 0) {
            offsetInfo = new byte[remainLen];
            fileInputStream.read(offsetInfo);
        } else {
            throw new RuntimeException("load file is error");
        }
        System.out.println("offsetAddr : " + offsetAddr);
        System.out.println("offsetOwner : " + offsetOwner);
        fileInputStream.close();
    }


    public long bytesToLong(byte[] array) {

        if (array.length > 8) {
            throw new RuntimeException("this is not long");
        } else {
            long result = 0l;
            for (int i = 0; i < array.length; i++) {
                result |= (array[i] & 0xffL) << i * 8;
            }
            return result;

        }

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


        long recordMin = 0;
        long recordMax = this.offsetAddr / baseLen - 1;
        long recordMid = (recordMin + recordMax) / 2 ;
        byte[] tmp4 = new byte[4];
        byte [] tmp8 = new byte[8];
        byte [] tmp12 = new byte[12];
        while (recordMax - recordMin >= 0){
            System.arraycopy(offsetInfo,(int) (recordMid*baseLen) ,tmp4,0,4);
            long minip = bytesToLong(tmp4);
            System.arraycopy(offsetInfo,(int) (recordMid*baseLen + 4) ,tmp4,0,4);
            long maxip = bytesToLong(tmp4);
            if(nip<minip){
                recordMax= recordMid - 1;
            }else  if(nip == minip || nip > minip && nip < maxip || nip == maxip){
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+8) ,tmp8,0,8);
                long addrBegin = bytesToLong(tmp8);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+16) ,tmp8,0,8);
                 long addrLength = bytesToLong(tmp8);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+24) ,tmp8,0,8);
                long ownerBegin = bytesToLong(tmp8);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+32) ,tmp8,0,8);
                long ownerLength = bytesToLong(tmp8);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+40) ,tmp12,0,12);
                String bdLon = new String(tmp12);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+52) ,tmp12,0,12);
                String bdLat = new String(tmp12);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+64) ,tmp12,0,12);
                String wgsLon = new String(tmp12);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+76) ,tmp12,0,12);
                String wgsLat = new String(tmp12);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+88) ,tmp12,0,12);
                String radius = new String(tmp12);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+100) ,tmp12,0,12);
                String scene = new String(tmp12);
                System.arraycopy(offsetInfo,(int) (recordMid*baseLen+112) ,tmp12,0,12);
                String accuracy = new String(tmp12);
                byte []addrBundle = new byte[(int)(addrLength)];
                System.arraycopy(offsetInfo,(int) (addrBegin) ,addrBundle,0,(int)(addrLength));
                String addrBundleStr = new String (addrBundle,Charset.forName("utf8"));
//                String [] addr = addrBundleStr.split("\\|");
                return addrBundleStr;

            }else if(nip > maxip){
                recordMin = recordMid + 1;
            }else{
                System.out.println("Error Case");
            }
            recordMid = (recordMin + recordMax) / 2;
        }


        return "Not Found";
    }

    public static void main(String[] args) throws Exception {
       IPLocate ipLocate = new IPLocate();
       ipLocate.loadDat("D://test//IP_trial_2018M06_single_WGS84.dat");
       String result = ipLocate.locateIp("1.204.54.213");
        System.out.println(result);
//        new IPLocate().loadDat("D://test//IP_trial_2018M06_single_WGS84.dat");
//      long result =   new IPLocate().ipToLong("1.204.54.213");
//        System.out.println(result);
    }

}
