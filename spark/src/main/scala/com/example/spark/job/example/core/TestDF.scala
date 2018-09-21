package com.example.spark.job.example.core

import com.example.spark.SimpleBean
import com.example.spark.common.SparkJob

object TestDF extends SparkJob {
    override protected def run(args: Array[String]): Unit = {
        val seq = (0 to 10000).toSeq
        val sql = sqlContext
        import sql.implicits._
        sc.parallelize(seq).repartition(30)//.saveAsTextFile()
            .toDF().createOrReplaceTempView()





    }
}
