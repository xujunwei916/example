package com.example.others.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestLog {

    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    public static void main(String[] args) {
//        logger.info("sskskskskks");
        Map<String ,String> map =new HashMap<String,String>();
        map.put("aaaa","bb");
        System.out.println(map);
    }

}
