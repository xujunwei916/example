package com.example.spark.job.example.core

import com.example.spark.common.SparkJob

object TestDF extends SparkJob {
    override protected def run(args: Array[String]): Unit = {
        val seq = (0 to 10000).toSeq
        val sql = sqlContext
        val rdds = sc.parallelize(seq).repartition(10)
            .randomSplit(Array(0.1,0.1,0.8))
        rdds.foreach(i=>println(i.count()))


    }
}
