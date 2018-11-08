package com.example.others.redis;

import org.apache.commons.lang.StringUtils;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedis {


//    public JedisConnectionFactory wJedisConnectionFactory() {
//
//
//        JedisPoolConfig poolCofig = new JedisPoolConfig();
//        poolCofig.setMaxIdle(200);
//        poolCofig.setMaxTotal(200);
//        poolCofig.setMaxWaitMillis(5000);
//        poolCofig.setTestOnBorrow(true);
//        poolCofig.setTestOnCreate(false);
//        poolCofig.setTestOnReturn(false);
//        poolCofig.setTestWhileIdle(true);
////        poolCofig.set
//
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
//        connectionFactory.setHostName("172.17.20.119");
//        connectionFactory.setPassword("rc_redis");
//        connectionFactory.setUsePool(true);
//        connectionFactory.setPort(6379);
//        connectionFactory.setTimeout(5000);
//        connectionFactory.setDatabase(8);
//        connectionFactory.setPoolConfig(poolCofig);
////        connectionFactory.
//        connectionFactory.afterPropertiesSet();
//
//        return connectionFactory;
//
//
//    }
//
//
//    public RedisTemplate<String, String> wRedisTemplates() {
//        JedisConnectionFactory connectionFactory = wJedisConnectionFactory();
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    public static Jedis getConnect(String host, int port, String password) {
//
//
//        Jedis jedis = new Jedis(host, port);
//        if (!StringUtils.isEmpty(password)) {
//            jedis.auth(password);
//        }
//
//        return jedis;
//
//    }
//
//
//    public static boolean setData(Jedis jedis, String key, String value) {
//        String result = jedis.set(key, value);
//        return true;
//    }


    public static void main(String[] args) {
//        ExecutorService Pool =Executors.newScheduledThreadPool(50);
//
//        TestRedis testRedis = new TestRedis();
//        RedisTemplate<String, String> template = testRedis.wRedisTemplates();
//        int count = 0;
//        long l = System.currentTimeMillis();
//        for (int i = 0; i < 1000000; i++) {
//            final int j =i;
//            Pool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    long start = System.currentTimeMillis();
//                    String key = "sjfksjdfjdsmfkdskf" + j;
//                    String hKey = "name";
//                    String value = "name" + j;
//                    template.opsForHash().put(key, hKey, value);
//                    template.expire(key, 7, TimeUnit.DAYS);
//                    hKey = "age";
//                    value = "age" + j;
//                    template.opsForHash().put(key, hKey, value);
//                    template.expire(key, 7, TimeUnit.DAYS);
//                    long end = System.currentTimeMillis();
//                    if (end - start > 100) {
//                        System.out.println(end - start);
//                    }
//                }
//            });
//            if(i%200==0){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        Pool.shutdown();
//        System.out.println(System.currentTimeMillis()-l);


    }


}
