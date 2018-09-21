package com.example.others.gson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * json 解析帮助类
 */
public class JsonHelper {

    public static ObjectMapper mapper = new ObjectMapper();

    /**
     * 转换 对象成json
     * 如果发生异常返回null
     */
    public static <T> String toJson(T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转换 对象成json
     * 如果发生异常返回null
     */
    public static <T> T json2Object(String value, Class<T> clazz){
        try {
            return mapper.readValue(value,clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {

//        TestBean testBean = new TestBean();
//        testBean.setId(100);
//        testBean.setClient("{\"aaa\":1}");
//        testBean.setData("kkkkkkk");
//        System.out.println(toJson(testBean));
        String json = "{\"id\":100,\"client\":{\"aaa\":1},\"data\":\"kkkkkkk\",\"data3\":\"kkkkkkk\"}";
        System.out.println(json2Object(json,TestBean.class));


    }
}
