package com.example.spark.job.example.core

import com.example.spark.common.SparkJob

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
  }
}