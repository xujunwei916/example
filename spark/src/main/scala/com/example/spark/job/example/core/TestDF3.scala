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
      import ss.implicits._
      val df =ss.read.option("header","true").csv("/user/Hypers/95_ad_config_00000..csv")
      df.printSchema()

//        val df = ss.createDataFrame(seq,classOf[Data])
////
        df.show(100)

//      spark.sql("")



//        df.write.option("compression","snappy").parquet("")


//       val rdd =  sc.parallelize(Seq(130,131),2).flatMap(
//           item=> (0 to 99 ).map(i=> 100*item+i)
//        )
////         .repartition(100)
////         .flatMap(
////             item=> (0 to 999 ).map(i=> 1000*item+i)
////         )
//
//       val df =  rdd.toDF("id")
//        df.show(1000)
//        println(df.count())








    }
}
