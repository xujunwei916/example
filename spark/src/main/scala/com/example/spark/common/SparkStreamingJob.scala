package com.example.spark.common

import com.example.others.log.ExampleLog
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.util.ShutdownHookManager
import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

abstract class SparkStreamingJob extends ExampleLog{

  protected  val sparkConf = new SparkConf


  protected  var ssc:StreamingContext = null

  protected var sc:SparkContext = null

  protected  var fs :FileSystem =null

  def setJobName(name: String) = {
    sparkConf.setAppName(name)
  }

  def set(key: String, value: String) = {
    sparkConf.set(key, value)
  }

  def init(soconds:Int): Unit ={

    ssc = new StreamingContext(sparkConf, Seconds(soconds))
    sc=ssc.sparkContext

    fs =FileSystem.get(ssc.sparkContext.hadoopConfiguration)
  }

  protected def initJob() = {
    sparkConf.setMaster("yarn")
    sparkConf.setAppName(this.getClass.getName+"_"+System.currentTimeMillis())
    init(10);

  }


  protected def run(args: Array[String]): Unit

  final def main(args: Array[String]): Unit = {

    logger.info("start job : "+ this.getClass.getName)
    logger.info("run job args : "+ args.mkString(","))
    try {

      this.initJob()
      this.run(args)
      this.start()
      logger.info("end job : "+ this.getClass.getName)
    } catch {
      case e: Exception => {
        e.printStackTrace()
        processError(e)
        logger.error("run job is ERROR : "+this.getClass.getName)
      }
    }
  }

  def close()={
    ssc.stop()
  }
  def start()={
    ssc.start()
    ssc.awaitTermination()
  }
  protected def processError(exception:Exception)={

  }

  private def stopOnShutdown(): Unit = {
    val stopGracefully = sparkConf.getBoolean("spark.streaming.stopGracefullyOnShutdown", false)

    // Do not stop SparkContext, let its own shutdown hook stop it

  }

}
