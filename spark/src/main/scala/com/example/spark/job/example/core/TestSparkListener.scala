package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions._

object TestSparkListener extends SparkJob {
    override protected def initJob() = {
        sparkConf.setMaster("local[3]")
        sparkConf.setAppName("TestDF")
        init()
        sc.addSparkListener( new MySparkListener)

    }


    override protected def run(args: Array[String]): Unit = {
        val seq = (0 to 200).toSeq
        val rdd = sc.parallelize(seq).repartition(10)

        val sum = rdd.reduce(_ + _)

        val count = rdd.count()
        println(sum)
        println(count)

    }
}
