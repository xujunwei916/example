package com.example.spark.job.example.streaming

import com.example.spark.common.SparkStreamingJob

/**
  * sparkString 监听一个端口的例子
  */

object PortMonitorExample extends SparkStreamingJob {
    override protected def run(args: Array[String]): Unit = {

        val lines = ssc.socketTextStream("127.0.0.1", 9999)
        val count = lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
        count.print()



    }

    override protected def initJob(): Unit = {
        sparkConf.setMaster("local[2]")
        sparkConf.setAppName("TestSparkStreaming")
        init(10)
        println(ssc.sparkContext.getConf.getAll.mkString("\n"))


    }
}
