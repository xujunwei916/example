package com.example.spark.test;

import java.util.Map;
import java.util.Properties;

public class TestClasspath {
    public static void main(String[] args) throws Exception {

        long time = Long.valueOf(args[0]) * 1000L;
        Map<String, String> env = System.getenv();
        System.out.println("****************************ENV START*****************************");
        for (Map.Entry<String, String> entry : env.entrySet()) {
            System.out.println(entry);
        }

        System.out.println("****************************ENV END*****************************");
        Properties props = System.getProperties();
        System.out.println("****************************PROPERTIES START*****************************");
        for (Map.Entry<Object, Object> entry : props.entrySet()) {
            System.out.println(entry);
        }
        System.out.println("****************************PROPERTIES END*****************************");


        Properties properties = new Properties();

        properties.load(TestClasspath.class.getClassLoader().getResourceAsStream("core.properties"));

        System.out.println("properties = " + properties);
        Thread.sleep(time);


    }
}
