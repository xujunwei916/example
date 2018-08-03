package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import org.apache.spark.sql.RowFactory
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object TestDF extends SparkJob{
    override protected def run(args: Array[String]): Unit =
    {

        val rdd = sc.textFile("")


        val schema = StructType(List(
            StructField("id",StringType,true),
            StructField("name",StringType,true),
            StructField("age",StringType,true)
        ))
          val rdd2=  rdd.map { x => {
                RowFactory.create(x._1,x._2,x._3)
            } }
    }
}
