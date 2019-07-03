package com.example.others.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class DeleteUidRedis {

    public static void main(String[] args) throws Exception {

//        for (int j = 0; j <1000 ; j++) {
        long start = System.currentTimeMillis();
        Jedis jedis = RedisOption.getConnect("172.17.19.14", 6385, "rc_redis");
        String cursor = "0";
        Pattern pattern = Pattern.compile("^[0-9A-F]{28}$");
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 100000; i++) {
            ScanResult<String> result = jedis.scan(cursor, new ScanParams().count(10000));
            cursor = result.getStringCursor();
            List<String> keys = result.getResult();
            System.out.println(keys.size());

            for (String key : keys) {
                if (pattern.matcher(key).matches()) {
                    pipeline.del(key);

                } else {
//                    System.out.println(key);
                }
            }
            pipeline.sync();
//                pipeline.close();

            cursor = result.getStringCursor();

            System.out.println(i);

        }
        jedis.close();
//            System.out.println("j = "+ j);

//        }


    }


}
