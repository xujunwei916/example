package com.example.others.redis;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RedisOption {

    public static Jedis getConnect(String host, int port, String password) {


        Jedis jedis = new Jedis(host, port);
        if (!StringUtils.isEmpty(password)) {
            jedis.auth(password);
        }

        return jedis;

    }


    public static boolean setData(Jedis jedis, String key, String value) {
        String result = jedis.set(key, value);
        return true;
    }

    public static void main(String[] args) {
        Jedis jedis = getConnect("localhost", 6379, null);
//        setData(jedis,"test_set","sss");
//        jedis.set("test_set2","ssssss","NX");
//        for (int i = 0; i <100000 ; i++) {
//                jedis.lpush("test_list","value"+i);
//        }
//        for (int i = 0; i <1000000 ; i++) {
//            jedis.hset("test_hash","f"+i,"v"+i);
//        }
        String tmp = "0";
        ScanParams scanParams = new ScanParams();
        scanParams.count(10000);
        int count =0;
        for (int i = 0; i < 100000; i++) {

            ScanResult<Map.Entry<String,String>> scanResult =jedis.hscan("test_hash", tmp,scanParams);
            tmp=scanResult.getStringCursor();
//            System.out.println();
//            for (Map.Entry<String,String> entry :scanResult.getResult()                 ) {
//                System.out.println(entry);
//            }
            System.out.println("get count = "+scanResult.getResult().size());
            System.out.println("tmp = "+tmp);
            count+=scanResult.getResult().size();
            if("0".equals(tmp)){
                System.out.println(i);
                break;
            }



        }
        System.out.println(count);
    }
}
