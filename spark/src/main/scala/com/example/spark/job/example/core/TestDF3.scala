package com.example.spark.job.example.core

import com.example.spark.bean.TestCreateList
import com.example.spark.brodcast.Data
import com.example.spark.common.SparkJob
import org.apache.spark.sql.Encoders

object TestDF3 extends SparkJob {
    override protected def initJob() = {
        sparkConf.setMaster("local[3]")
        sparkConf.setAppName("TestDF2")
        init()

    }


    override protected def run(args: Array[String]): Unit = {

        val ss = spark
        val seq = TestCreateList.getList

        val df = ss.createDataFrame(seq,classOf[Data])

        df.show(100)


        df.rdd.foreachPartition(row=>{
            row.map()
        })






    }
}
