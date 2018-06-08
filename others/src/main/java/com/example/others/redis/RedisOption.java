package com.example.others.redis;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
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
//        Jedis jedis = getConnect("172.17.20.137", 6379, "123456");
//        setData(jedis,"test_set","sss");
//        jedis.set("test_set2","ssssss","NX");
//        for (int i = 0; i <100000 ; i++) {
//                jedis.lpush("test_list","value"+i);
//        }
//        for (int i = 0; i <1000000 ; i++) {
//            jedis.hset("test_hash","f"+i,"v"+i);
//        }
//        String tmp = "0";
//        ScanParams scanParams = new ScanParams();
//        scanParams.count(10000);
//        int count =0;
//        for (int i = 0; i < 100000; i++) {
//
//            ScanResult<Map.Entry<String,String>> scanResult =jedis.hscan("test_hash", tmp,scanParams);
//            tmp=scanResult.getStringCursor();
////            System.out.println();
////            for (Map.Entry<String,String> entry :scanResult.getResult()                 ) {
////                System.out.println(entry);
////            }
//            System.out.println("get count = "+scanResult.getResult().size());
//            System.out.println("tmp = "+tmp);
//            count+=scanResult.getResult().size();
//            if("0".equals(tmp)){
//                System.out.println(i);
//                break;
//            }
//
//
//
//        }
//        System.out.println(count);
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(1);
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("172.17.20.137", 6379));
        nodes.add(new HostAndPort("172.17.20.137", 6380));
        nodes.add(new HostAndPort("172.17.20.137", 6381));
        nodes.add(new HostAndPort("172.17.20.137", 6382));
        nodes.add(new HostAndPort("172.17.20.137", 6383));
        nodes.add(new HostAndPort("172.17.20.137", 6384));
        JedisCluster cluster = new JedisCluster(nodes, 1000,1000,1,"123456",poolConfig);


//        System.out.println(cluster.get("aaa"));
//        System.out.println(cluster.get("bbbb"));
//        System.out.println(cluster.get("test001"));
//        System.out.println(cluster.get("test002"));
//
//        for (int i = 0; i <1000 ; i++) {
//            cluster.hincrBy("test_incr","abc",1);
//
//        }
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
