package com.example.others.redis;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RedisScanThread {

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


        Jedis jedis = getConnect("172.17.20.180", 6385, "rc_redis");
        String cursor = "0";
        CountVO vo = new CountVO();
        ExecutorService POOL = Executors.newScheduledThreadPool(50);

        jedis.select(4);
        while (true) {
            ScanResult<String> result = jedis.scan(cursor,new ScanParams().count(10000));
            cursor = result.getStringCursor();
            List<String> keys= result.getResult();

            for (String key : keys) {
                String [] tmp = jedis.get(key).split(";");
                for (int i = 0; i < tmp.length && i<4 ; i++) {
                    if("游戏".equals(tmp[i])){

                    }

                }
            }
            if (cursor == null || "0".equals(cursor)) {
                break;
            }

        }
        POOL.shutdown();

        System.out.println(vo);


        jedis.close();
    }
}
