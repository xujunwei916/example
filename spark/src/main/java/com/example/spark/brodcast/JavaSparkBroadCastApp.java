package com.example.spark.brodcast;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;

import java.util.Arrays;
import java.util.List;

/**
 * Spark使用广播变量进行计算
 */
public class JavaSparkBroadCastApp {
    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            System.err.println("Usage :<times[Integer]>");
            System.exit(-1);
        }
        SparkConf conf = new SparkConf();
        conf.setAppName(JavaSparkBroadCastApp.class.getSimpleName());
        conf.setMaster("local[2]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> listRDD = sc.parallelize(list);

        int times = Integer.valueOf(args[0].trim());
        //设置一个广播变量来解决
        Broadcast<Integer> timesBC = sc.broadcast(times);


        //将集合中的每一个元素*times
        JavaRDD<Integer> retRDD = listRDD.map(ele -> {
            return ele * timesBC.value();
        });

        retRDD.foreach(new VoidFunction<Integer>() {
            @Override
            public void call(Integer integer) throws Exception {
                timesBC.value();
            }
        });
    }
}