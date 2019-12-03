package com.example.others.gson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Test {

    private static final Logger log = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) throws Exception {
//        ObjectMapper om = new ObjectMapper();
//        String json = "{\"aaa\":\"dddd\",\"bb\":10,\"cc\":1.001,\"dd\":\"200001\",\"ee\":{\"ff\":\"dd\"}}";
//
//        JsonNode node = om.readTree(json);
//
//        ObjectNode top = null;
//
//        if(node instanceof ObjectNode){
//
//            top = (ObjectNode) node;
//        }
//        top.put("ssss","你好");
//
//        System.out.println(om.writeValueAsString(top));
        log.error("Exception: {}vewtert" ,new RuntimeException("sss").toString());


//        ObjectNode jsonNode = om.createObjectNode();
//        jsonNode.put("sss","sss");
//
//        System.out.println(om.writeValueAsString(jsonNode));


//        Parameter p = om.readValue("{\"prevValue\": 101044.0123423435433333333333333}", Parameter.class);
//        System.out.println(p.prevValue);
    }

    public static class Number2StringDesrializer extends JsonDeserializer<String> {

        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            Number n = p.getDecimalValue();
            return n.toString();
        }

    }

    public static class Parameter {

        @JsonDeserialize(using = Number2StringDesrializer.class)
        private String prevValue;

        public void setPrevValue(String prevValue) {
            this.prevValue = prevValue;
        }


        public String getPrevValue() {
            return prevValue;
        }
    }
}