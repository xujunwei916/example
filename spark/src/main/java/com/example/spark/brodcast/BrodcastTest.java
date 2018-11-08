package com.example.spark.brodcast;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StringType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrodcastTest {

    public static void main(String[] args) {
//        SP
//    }
        SparkSession sparkSession = SparkSession.builder().appName("test").master("local[3]").getOrCreate();
        List<String > data = new ArrayList<>();
        data.add("1");
        data.add("2");

        Dataset<Row> df = sparkSession.createDataFrame(data,String.class).toDF("a");

        df.show();
        sparkSession.stop();
//       df.withColumn("aaa",new Column("a+b+c+d"));
//                .flatMap((FlatMapFunction<Row, Object>) row -> new ArrayList<Object>().iterator(),null);
//        new Thread(()-> System.out.println(1111));
//        List<String> list = new ArrayList<String>();
//        list.forEach(System.out::println);



//        sparkSession.read().
    }

}
