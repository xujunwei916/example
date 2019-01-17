package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import org.apache.hadoop.io.compress.SnappyCodec
import org.apache.spark.SparkFiles
import org.apache.spark.sql.SaveMode

/**
  * wordcount 例子
  * @author JW
  */
object TestCheckPoint extends SparkJob{
  val chechpointPath = "/user/xujw/test_point"


  override protected def initJob: Unit = {
    sparkConf.setAppName("test_count_count")
    init()
  }

  override protected def run(args: Array[String]): Unit = {
    val Array(input,output) =args
    sc.setCheckpointDir(chechpointPath)
    sc.setCheckpointDir(chechpointPath)
   val df = spark.read.textFile(input)


  }
}
