package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import org.apache.hadoop.io.compress.SnappyCodec
import org.apache.spark.SparkFiles
import org.apache.spark.sql.SaveMode

/**
  * wordcount 例子
  * @author JW
  */
object WordCountExample extends SparkJob{


  override protected def initJob: Unit = {
    sparkConf.setMaster("local[2]")
    sparkConf.setAppName("test_count_count")
    init()
  }

  override protected def run(args: Array[String]): Unit = {
    val rdd = sc.textFile("d:/test/test.txt")
    val result = rdd.flatMap(_.split("\\s+")).map((_,1)).reduceByKey(_+_)
    result.collect().foreach(println(_))
    rdd.saveAsTextFile("/tmp",classOf[SnappyCodec])
    sc.hadoopConfiguration.set("mapreduce.output.fileoutputformat.compress","true")
    sc.hadoopConfiguration.set("mapreduce.output.fileoutputformat.compress.codec","org.apache.hadoop.io.compress.SnappyCodec")
//    rdd.saveAsObjectFile()
    SparkFiles.get("")
    val sql =sqlContext
    import sql.implicits._
    result.mapPartitions(sign=>sign.toList.iterator)
    rdd.toDF("a","b").write.bucketBy(3, "name").mode(SaveMode.Append).insertInto("")


  }
}
