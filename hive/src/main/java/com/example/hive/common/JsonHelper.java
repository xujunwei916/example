package com.example.hive.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * json 解析帮助类
 */
public class JsonHelper {

    public final static String PREFIX_SPLIT = "\u0001";
    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
    }

    /**
     * 转换 对象成json
     * 如果发生异常返回null
     *
     * @param value 需要转换成json的对象
     * @param <T>   泛型参数
     * @return json字符串
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
     *
     * @param value json字符串
     * @param clazz 要转换的类型的对象
     * @param <T>   泛型
     * @return 泛型对应的java对象
     */
    public static <T> T json2Object(String value, Class<T> clazz) {
        try {
            return mapper.readValue(value, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 解析json数据通过索引找到相应的值
     *
     * @param jsonMap json字符串转换转换成的jsonobject 的map
     * @param keys    获取数据的索引
     * @return 对应的值
     */
    public static String getJsonMapValue(Map<String, Object> jsonMap, String... keys) {
        //无索引的时候返回null
        if (keys == null || keys.length == 0 || jsonMap == null) {
            return null;
        } else if (keys.length == 1) {
            Object value = jsonMap.get(keys[0]);
            if (value == null) {
                return null;
            } else if (value instanceof Map || value instanceof List) {
                return JsonHelper.toJson(value);
            } else {
                return value.toString();
            }
        } else {
            Object value = jsonMap.get(keys[0]);
            if (value instanceof Map) {
                return getJsonMapValue((Map<String, Object>) value, Arrays.copyOfRange(keys, 1, keys.length));
            } else {
                return null;
            }
        }
    }


    /**
     * json解析后的值转换成String
     *
     * @param value object 转换后的数据
     * @return 转换String的值
     */
    public static String object2String(Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof List || value instanceof Map) {
            return JsonHelper.toJson(value);
        } else {
            return value.toString();
        }
    }


    public static void main(String[] args) {
        ExecutorService Pool = Executors.newScheduledThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            Pool.submit(new Runnable() {

                @Override
                public void run() {
                    String json = "{\"value\":[1,2,3,4,5,6]}";
                    Map result = JsonHelper.json2Object(json, Map.class);

                    System.out.println("result = " + result);
                }
            });
        }
        Pool.shutdown();

    }
}
