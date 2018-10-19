package com.example.spark.brodcast;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrodcastTest {

    public static void main(String[] args) {
//        SP
//    }
        SparkSession sparkSession = SparkSession.builder().getOrCreate();
        sparkSession.read().orc("aaa").flatMap((FlatMapFunction<Row, Object>) row -> new ArrayList<Object>().iterator(),null);
        new Thread(()-> System.out.println(1111));
        List<String> list = new ArrayList<String>();
        list.forEach(System.out::println);

//        sparkSession.read().
    }

}
