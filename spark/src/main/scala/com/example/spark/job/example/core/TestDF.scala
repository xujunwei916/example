package com.example.spark.job.example.core

import java.util.Date

import com.example.spark.common.SparkJob

object TestDF extends SparkJob {
    override protected def run(args: Array[String]): Unit = {
        val seq = (0 to 10000).toSeq
        val sql = sqlContext
        val rdd = sc.parallelize(seq).repartition(10)
        //            .randomSplit(Array(0.1,0.1,0.8))
        //        rdds.foreach(i=>println(i.count()))
//        import sql.implicits._
        val sparkTmp = spark
        import sparkTmp.implicits._
        val DF = rdd.map(item => (item, item * item)).toDF("a", "b")
        import org.apache.spark.sql.functions._
        val fun:(String)=>String = (a) => new Date().toString
        val sqlfunc = (udf(fun)).apply(col("a"))
//        val c = sqlfunc(col("a"))
        DF.withColumn("c", sqlfunc).show()


    }
}
