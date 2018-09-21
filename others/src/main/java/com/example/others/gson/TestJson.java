package com.example.others.gson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJson {
    Map<String,Object>  map  =  new HashMap<>();

    public static void main(String[] args) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        String json = "{\"action\":1.0E9}";
//       Map<String ,Object> map1= mapper.readValue(json, Map.class);
//        for (Map.Entry<String,Object> entry:map1.entrySet()) {
//            System.out.println(entry.getKey()+""+entry.getValue().getClass());
//            System.out.println(entry.getValue() instanceof List);
//
//        }
//        System.out.println(map1);
        System.out.println(1000000000000000000000000000.0+"");
    }
}
