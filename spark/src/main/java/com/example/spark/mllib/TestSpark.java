package com.example.spark.mllib;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.sources.In;

import scala.Tuple2;

public class TestSpark {
    public static void main(String[] args) {


        JavaSparkContext context = new JavaSparkContext(new SparkConf());

//        context.textFile("aaa").flatMapToPair(new Function<String, Tuple2<String,Integer>>() {
//            @Override
//            public Tuple2<String,Integer> call(String v1) throws Exception {
//                return null;
//            }
//        })
        JavaPairRDD<String,String> aaa= null;

//        S
//        aaa.mapValues()





    }
}
