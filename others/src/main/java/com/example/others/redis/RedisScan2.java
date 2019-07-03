package com.example.others.redis;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class RedisScan2 {

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
        Jedis jedis = getConnect("172.16.0.180", 6380, "rc_redis");
        Calendar calendar =Calendar.getInstance();
        calendar.set(2019,3,25,17,40,0);

        SimpleDateFormat sss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i <1000 ; i++) {
            calendar.add(Calendar.SECOND,1);
            String tmp = sss.format(calendar.getTime());
            String key ="spark:AdOutputControl:showAd:sec:"+tmp;
            Map<String,String> map= jedis.hgetAll(key);
//            System.out.println(key);
//            System.out.println(map);
            if(map.containsKey("100017")){
                System.out.println(tmp+","+map.get("100017"));
            }

        }
//        while (true) {
//            ScanResult<String> result = jedis.scan(cursor,new ScanParams().count(10000));
//            cursor = result.getStringCursor();
//            List<String> keys= result.getResult();
//            count+=keys.size();
//            for (String key : keys) {
//                String [] tmp = jedis.get(key).split(";");
//                for (int i = 0; i < tmp.length && i<4 ; i++) {
//                    if("游戏".equals(tmp[i])){
//                        rCount++;
//                    }
//
//                }
//            }
//            if (cursor == null || "0".equals(cursor)) {
//                break;
//            }
//            System.out.println(count);
//            System.out.println(rCount);
//        }



        jedis.close();
    }
}
