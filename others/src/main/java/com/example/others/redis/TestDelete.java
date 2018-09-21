package com.example.others.redis;

import java.util.Random;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class TestDelete {

    public static void main(String[] args) throws Exception{
        long start =System.currentTimeMillis();
        Jedis jedis = RedisOption.getConnect("127.0.0.1", 6379, "123456");
        int count=0;
        Random random = new Random();
        boolean flag = true;

        while (flag){
            jedis.select(2);
            jedis.del(random.nextInt(1000000)+"");
            count++;
            if(count>=100000){
                count=0;
                long end= System.currentTimeMillis();
                System.out.println(end-start);
                start =System.currentTimeMillis();
            }

        }
        jedis.close();

    }


}
