package com.example.spark.job.example.core

import com.example.spark.common.SparkJob
import org.apache.hadoop.io.compress.SnappyCodec
import org.apache.spark.SparkFiles
import org.apache.spark.sql.SaveMode

/**
  * wordcount 例子
  *
  * @author JW
  */
object WordCountExample2 extends SparkJob {


  override protected def initJob: Unit = {
    sparkConf.setMaster("local[2]")
    sparkConf.setAppName("test_count_count")
    init()
  }

  override protected def run(args: Array[String]): Unit = {

    val seqOp = (x: (Int, Int), y: Int) => (x._1 + y, x._2 + 1)
    val comOp = (x: (Int, Int), y: (Int, Int)) => (x._1 + y._1, x._2 + y._2)

   val a =  sc.parallelize(Seq(1, 2, 3, 4), 2).aggregate((0, 0))(seqOp, comOp)
    println(a)


  }
}
