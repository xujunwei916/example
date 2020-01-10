package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions._

object TestDF2 extends SparkJob {
    override protected def initJob() = {
        sparkConf.setMaster("local[3]")
        sparkConf.setAppName("TestDF2")
        init()

    }


    override protected def run(args: Array[String]): Unit = {

        val ss = spark
        import ss.implicits._
        val df = ss.read.csv("D:\\test\\cheat\\result.csv")
            .toDF("dt", "ti", "cost", "app", "hr", "mi")
            .groupBy("dt", "hr", "app").count()
            .orderBy("dt", "hr")
        df.repartition(1).write.csv("D:\\test\\cheat\\result2")


        ss.read.orc()

    }
}
