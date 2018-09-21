package com.example.spark;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class SimpleBean {
    public static Map<String, String> instance;


    public static  Jedis jedis;
//    val s = "172.17.20.180:6379"
//    val redisList = Utils.getRedisConfs(s)
//            .map(redisConf => {
//            val j = new JedisPool(new GenericObjectPoolConfig(),
//            redisConf._1,
//            redisConf._2,
//            10000).getResource
//            j.auth(redisConf._3)
//            j.select(0)
//            (j, j.pipelined)
//}).toList

    public static Map<String, String> getInstance() {
        if (instance == null) {
            System.out.println("intance is create");
            instance = new HashMap<>();
        }
        return instance;

    }
    public static Jedis getJedis(){
        if(jedis==null){
            System.out.println("intance is create jedis");
            jedis = new Jedis("172.17.20.220",6379);
            jedis.auth("123456");
        }
        return jedis;
    }
}
