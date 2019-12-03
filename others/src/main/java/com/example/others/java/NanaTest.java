package com.example.others.java;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NanaTest {

    public static void main(String[] args) throws Exception {

        Map<String,String> map = new TreeMap<>();


        map.put("project","qkk");
        map.put("requestTime","1572850566");
        map.put("processList","[{\"projectSN\":\"XQ1U1567160430Y20190831T1\",\"passId\":55564,\"tradingValue\":8600,\"taskId\":\"1_2\",\"tradingTime\":1567160430,\"desc\":\"测试啦啦啦test@#$%^&*(()*(<>?2542364\"}]");


        List<String> query = new ArrayList();

        for (Map.Entry<String,String> entry: map.entrySet()) {
            query.add(entry.getKey()+""+URLEncoder.encode(entry.getValue(),"utf-8"));
        }
        String tmp = StringUtils.join(query,"&")+"xq_userlevel";


       String result = encryptMD5(tmp);
        System.out.println(result);







    }

    public static String encryptMD5(String dataStr) {
        try {
            dataStr = dataStr;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
