package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import scala.collection.JavaConversions._

/**
  * wordcount 例子
  *
  * @author JW
  */
object TestSparkClasspath extends SparkJob {


    override protected def initJob: Unit = {
        sparkConf.setMaster("yarn")
        sparkConf.setAppName("test_spark_classpath")
        init()
    }

    override protected def run(args: Array[String]): Unit = {
        val input = args(0)
        val time = args(1).toInt*1000L
        val env = System.getenv()
        println("****************************ENV START*****************************")
        env.foreach(println(_))
        println("****************************ENV END*****************************")
        val props = System.getProperties
        println("****************************PROPERTIES START*****************************")
        props.foreach(println(_))
        println("****************************PROPERTIES END*****************************")


        Thread.sleep(time)


        val rdd = sc.textFile(input).repartition(3)
        rdd.map((_, 1)).reduceByKey(_ + _).foreachPartition(
            sign => {
                val env = System.getenv()
                println("****************************ENV START*****************************")
                env.foreach(println(_))
                println("****************************ENV END*****************************")
                val props = System.getProperties
                println("****************************PROPERTIES START*****************************")
                props.foreach(println(_))
                println("****************************PROPERTIES END*****************************")
                sign.foreach(i => Thread.sleep(1L))
            }

        )

    }
}
