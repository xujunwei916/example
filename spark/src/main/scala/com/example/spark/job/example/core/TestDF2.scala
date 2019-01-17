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
        val df = ss.read.csv("D:\\test\\test\\lahuo\\1.csv")
            .toDF("sid", "taskid").select($"sid", explode(split($"taskid", ",")))
            .toDF("sid", "taskid")
        val df2 = ss.read.csv("D:\\test\\test\\lahuo\\task_package.txt")
            .toDF("date", "taskid", "package", "success", "failed", "flow")
        df2.join(df, Seq("taskid"), "left")
            .select("date", "sid", "package", "success", "failed", "flow")
            .groupBy("date", "sid", "package").agg(sum($"success"), sum($"failed"), sum($"flow"))
            .toDF("date", "sid", "package", "success", "failed", "flow")
            .orderBy("date", "sid", "package")
            .repartition(1)
            .write.mode(SaveMode.Overwrite).csv("D:\\test\\test\\lahuo\\2")

        val rdd  = sc.parallelize(Seq("a","a"))

//        spark.createDataFrame()


//        df.withColumn("date", $"")
        

    }
}
