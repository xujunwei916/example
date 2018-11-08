package com.example.others.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;

public class StringComplex {

    public static void main(String[] args) {
        String s="游戏:191440|191441|259255|261206|260349|261958|262575|;军事:259797|259795|259798|262347|262241|262560|262246|;财经:261039|261021|262527|262529|262454|261828|262612|;热门:260583|253767|248996|262405|262403|262407|262201|;汽车:252793|254803|252796|249925|262624|262634|262637|;旅游:262070|520|732|678|960|962|963|;购物:262627|262621|240989|257667|241368|241369|259581|;生活:262622|260191|262618|262604|262444|262446|262603|;";
//        System.out.println(s.getBytes().length);
//        long start = System.currentTimeMillis();
//        for (int i = 0; i <1000000 ; i++) {
//            if(i%10000==0){
//                System.out.println(i);
//            }
//            StringCompress.gzipDeflaterCompress(s);
//        }
//        System.out.println(System.currentTimeMillis()-start);
//        System.out.println(StringCompress.gzipDeflaterCompress(s).length);
        Map<String,List<Integer>> hash =  new HashMap<String, List<Integer>>();

        String cates[] = s.split(";");
        for (int i = 0; i <cates.length; i++) {
            String cate = cates[i].substring(0,2);
            String ggids = cates[i].substring(3);
            List<Integer> ggidResult =  new ArrayList<>();
            for (String ggid:ggids.split("\\|")) {
                ggidResult.add(Integer.valueOf(ggid));
            }
            hash.put(cate,ggidResult);
        }
        System.out.println(hash);


//        String ip = "172.17.20.180";
//        short port = 6379;
//        String passwd = "rc_redis";
//        Jedis redis = new Jedis(ip, port, 30 * 1000);
//        redis.auth(passwd);
//        redis.select(3);
//
//        redis.set("lookalike-000027099AAE6AADDD97A027F8D2".getBytes(), StringCompress.gzipCompress(s));
//        redis.expire("lookalike-000027099AAE6AADDD97A027F8D2", 86400 * 15);
//        redis.close();

//        String ip = "172.17.20.180";
//        short port = 6379;
//        String passwd = "rc_redis";
//        Jedis redis = new Jedis(ip, port, 30 * 1000);
//        redis.auth(passwd);
//        redis.select(6);
//
//        redis.("lookalike-000027099AAE6AADDD97A027F8D2".getBytes(), StringCompress.gzipCompress(s))
//        redis.expire("lookalike-000027099AAE6AADDD97A027F8D2", 86400 * 15);
//        redis.close();
    }
}
