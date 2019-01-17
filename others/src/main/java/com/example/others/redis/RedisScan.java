package com.example.others.redis;

import org.apache.commons.lang.StringUtils;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RedisScan {

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

    public static void main(String[] args) throws Exception {


        long start = System.currentTimeMillis();
        Jedis jedis = getConnect("172.17.20.180", 6385, "rc_redis");
//        Pipeline pipeline = jedis.pipelined();
        String cursor = "0";
        int count = 0;
        int rCount = 0;
        jedis.select(4);
        while (true) {
            ScanResult<String> result = jedis.scan(cursor,new ScanParams().count(10000));
            cursor = result.getStringCursor();
            List<String> keys= result.getResult();
            count+=keys.size();
            for (String key : keys) {
                String [] tmp = jedis.get(key).split(";");
                for (int i = 0; i < tmp.length && i<4 ; i++) {
                    if("游戏".equals(tmp[i])){
                        rCount++;
                    }

                }
            }
            if (cursor == null || "0".equals(cursor)) {
                break;
            }
            System.out.println(count);
            System.out.println(rCount);
        }

        System.out.println("last = " + count);
        System.out.println("last = " + rCount);


        jedis.close();
    }
}
