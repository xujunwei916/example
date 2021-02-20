package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import org.apache.hadoop.io.compress.SnappyCodec
import org.apache.spark.SparkFiles
import org.apache.spark.sql.SaveMode

/**
  * wordcount 例子
  *
  * @author JW
  */
object WordCountExample extends SparkJob {


  override protected def initJob: Unit = {
    sparkConf.setMaster("local[2]")
    sparkConf.setAppName("test_count_count")
    init()
  }

  override protected def run(args: Array[String]): Unit = {

    //  val df1 = spark.sql("select * from haa_data where ")
    //    df1.select("session","type_params.PLACEMENT")

    val ss = spark
    import ss.implicits._

    val csvDF = spark.read.option("multiLine", true).csv("D:\\test\\FMOB.csv")

    csvDF.show(100,false)
//    val seq = Seq((1, Map("a" -> "1", "b" -> "2"),"c"), (2, Map("c" -> "1", "d" -> "2"),"d"))
//    val df = sc.parallelize(seq).toDF("id","props","name")
//    df.select("props.a").show(false)
//    df.printSchema()
//
//    val c = ("s","s",1)


    //
    //   val  ds =  spark.read.text("d:/test/test.txt")
    //
    //    ds.show(10,false)
    //    val count1 = ds.count()
    //
    //    val countDs = ds.groupBy("value").count()
    //
    //    println(count1)
    //    countDs.show(100,false)
    //
    //    ds.createOrReplaceTempView("t1")
    //
    //   val df4=  spark.sql("select value,count(1) sss from t1 group by value")
    //
    //    df4.show(100,false)


    //    rdd.map(row=>(row,1)).toDF("","")


    //    val result = rdd.flatMap(_.split("\\s+")).map((_,1)).reduceByKey(_+_)
    //    result.collect().foreach(println(_))
    //    rdd.saveAsTextFile("/tmp",classOf[SnappyCodec])
    //    sc.hadoopConfiguration.set("mapreduce.output.fileoutputformat.compress","true")
    //    sc.hadoopConfiguration.set("mapreduce.output.fileoutputformat.compress.codec","org.apache.hadoop.io.compress.SnappyCodec")
    ////    rdd.saveAsObjectFile()
    //    SparkFiles.get("")
    //    val sql =sqlContext
    //    import sql.implicits._
    //    result.mapPartitions(sign=>sign.toList.iterator)
    //    rdd.toDF("a","b").write.bucketBy(3, "name").mode(SaveMode.Append).insertInto("")


  }
}
