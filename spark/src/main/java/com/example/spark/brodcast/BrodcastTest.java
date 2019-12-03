package com.example.spark.brodcast;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import scala.collection.JavaConverters;
import scala.collection.Seq;

public class BrodcastTest {

    public static void main(String[] args) {
//        SP
//    }
        SparkSession sparkSession = SparkSession.builder().appName("test").master("local[3]").getOrCreate();

//        sparkSession.sparkContext()
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("b");
        Seq<String> seq = JavaConverters.asScalaIteratorConverter(list.iterator()).asScala().toSeq();
        List<Data > datas= new ArrayList<>();
        datas.add(new Data("xujw",1));
        datas.add(new Data("xujw2",2));
        Dataset<Row> df = sparkSession.createDataFrame(datas,Data.class);
        df.write().mode(SaveMode.Append).partitionBy("name").orc("/tmp/sssss");
        df.show();
//        Dataset<Row> df2 = df.toDF("id2","name");
//        Dataset<Row> result = df.join(df2,toSeq("name"),"left");
//        result.show();
        sparkSession.stop();
//       df.withColumn("aaa",new Column("a+b+c+d"));
//                .flatMap((FlatMapFunction<Row, Object>) row -> new ArrayList<Object>().iterator(),null);
//        new Thread(()-> System.out.println(1111));
//        List<String> list = new ArrayList<String>();
//        list.forEach(System.out::println);



//        sparkSession.read().
    }


    public static Seq<String> toSeq(String ... values){

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(values));
        Seq<String> tmpSeq = JavaConverters.asScalaIteratorConverter(list.iterator()).asScala().toSeq();
        return tmpSeq;
    }

}