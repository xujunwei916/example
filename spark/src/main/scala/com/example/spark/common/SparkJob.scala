package com.example.spark.common

import com.example.others.log.ExampleLog
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}

abstract class SparkJob extends ExampleLog{

  protected  val sparkConf = new SparkConf

//  protected val spark= SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()
  protected  var spark:SparkSession =null

  protected  var sc:SparkContext =null

  protected  var sqlContext :SQLContext =null

  protected  var fs :FileSystem =null

  def setJobName(name: String) = {
    sparkConf.setAppName(name)
  }

  def set(key: String, value: String) = {
    sparkConf.set(key, value)
  }

  def init(): Unit ={
    spark =SparkSession.builder().config(sparkConf).getOrCreate()
    sc=spark.sparkContext
    sqlContext=spark.sqlContext
    fs =FileSystem.get(sc.hadoopConfiguration)
  }

  protected def initJob() = {
    sparkConf.setMaster("local[*]")
    sparkConf.setAppName(this.getClass.getName+"_"+System.currentTimeMillis())
    init()

  }


  protected def run(args: Array[String]): Unit

  final def main(args: Array[String]): Unit = {

    logger.info("start job : "+ this.getClass.getName)
    logger.info("run job args : "+ args.mkString(","))
    try {

      this.initJob()
      this.run(args)
      logger.info("end job : "+ this.getClass.getName)
    } catch {
      case e: Exception => {
        e.printStackTrace()
        logger.error("run job is ERROR : "+this.getClass.getName)
      }
    }
  }

  def close()={
    spark.close()
  }
  protected def processError(exception:Exception)={

  }

}
