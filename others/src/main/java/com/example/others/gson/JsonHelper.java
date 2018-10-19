package com.example.others.gson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * json 解析帮助类
 *
 */
public class JsonHelper {

    public static ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS,true);
//        mapper.configure(DeserializationFeature.)
    }

    /**
     * 转换 对象成json
     * 如果发生异常返回null
     * @param value 需要转换成json的对象
     * @param <T> 泛型参数
     * @return json字符串
     */
    public static <T> String toJson (T value){
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
     * @param value  json字符串
     * @param clazz 要转换的类型的对象
     * @param <T> 泛型
     * @return  泛型对应的java对象
     */
    public static <T> T json2Object(String value, Class<T> clazz){
        try {
            return mapper.readValue(value,clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void json2Map(String json){

        try {
            JsonNode  node = mapper.readTree(json);
            System.out.println(node.get("decimal").decimalValue());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
//        long dd = 2222222222222222222L;
//        TestBean testBean = new TestBean();
//        testBean.setDecimal(new BigDecimal(Double.valueOf(dd).toString()));
//        System.out.println(toJson(testBean));
//        System.out.println(testBean.getDecimal().toString());
//        System.out.println(dd);


        Map<String , Object > map = new HashMap<>();

//        String json = "{\"ob\":{\"decimal\":2.22222222222222222222222222}}";
        String json ="{\"decimal\":2.22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222}";
//        json2Map(json);
        Map<String,Object> map1 = json2Object(json,Map.class);

//        System.out.println(testBean.getDecimal());
//        System.out.println(toJson(testBean));



////        map = json2Object(json,map.getClass());
        for (Map.Entry<String,Object> entry: map1.entrySet()
                ) {
            System.out.println(entry);
            System.out.println(entry.getValue().getClass().getName());

        }





        
    }
}
