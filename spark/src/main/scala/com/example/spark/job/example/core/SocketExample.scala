package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import org.apache.hadoop.io.compress.SnappyCodec
import org.apache.spark.SparkFiles
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.streaming.OutputMode

/**
  * wordcount 例子
  *
  * @author JW
  */
object SocketExample extends SparkJob {


    override protected def initJob: Unit = {
        sparkConf.setMaster("local[2]")
        sparkConf.setAppName("test_count_count")
        init()
    }

    override protected def run(args: Array[String]): Unit = {
        val ss = spark
        import ss.implicits._
        val lines=  spark.readStream
            .format("socket")
            .option("host","localhost")
            .option("port","9999")
            .load()
//        val result = lines.writeStream.outputMode("console").start()

        val words =
            lines.as[String].flatMap(_.split("\\s+"))
//        val wordCount = words.groupBy($ "value").count()
//        val query = wordCount.writeStream.outputMode("update").format("console").start()
//        query.awaitTermination()

    }
}
