package com.example.spark.job.example.core

import org.apache.spark.scheduler.{SparkListener, SparkListenerTaskStart}

class MySparkListener extends SparkListener {
    override def onTaskStart(taskStart: SparkListenerTaskStart): Unit = {


        println("*************************************************")
        println(s"taskStart:${taskStart.stageId}")
        println("*************************************************")


    }

}
