package com.example.spark.job.example.streaming

import com.example.spark.common.SparkStreamingJob
import org.apache.spark.streaming.Minutes

/**
  * sparkString 监听一个端口的例子
  * 验证一些特殊的方法
  */

object PortMonitorFuncExample extends SparkStreamingJob {
    override protected def run(args: Array[String]): Unit = {

        val lines = ssc.socketTextStream("127.0.0.1", 9999)
        val pair = lines.flatMap(_.split(" ")).map((_, 1))
//        pair.print()
          pair.updateStateByKey[Int](updateFunction _).repartition(1)
              .saveAsTextFiles("d://test//file//file")


    }

    override protected def initJob(): Unit = {
        sparkConf.setMaster("local[4]")
        sparkConf.setAppName("TestSparkStreaming")
        init(10)
        println(ssc.getState())
       ssc.checkpoint("d://test//checkpoint_test2")
//        ssc.remember(Minutes(5))


    }
    def updateFunction(newValues: Seq[Int], runningCount: Option[Int]): Option[Int] = {
        val newCount = if(newValues.isEmpty) 0 else newValues.reduce(_ + _)
        Some(newCount+runningCount.getOrElse(0))
    }
}
