package com.example.others.gson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws Exception {
        ObjectMapper om = new ObjectMapper();
        Parameter p = om.readValue("{\"prevValue\": 101044.0123423435433333333333333}", Parameter.class);
        System.out.println(p.prevValue);
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