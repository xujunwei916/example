package com.example.spark.job.example.mllib

import com.example.spark.common.SparkJob
import org.apache.spark.ml.classification.GBTClassificationModel
import org.apache.spark.ml.{PipelineModel, PipelineModelTest}

object TestModelPredict extends SparkJob{
    def run(args: Array[String]): Unit = {
        val data= spark.read.parquet("file:///D:\\test\\model\\2018-11-05\\stages\\5_logreg_08fbf9576f24\\data")
        data.printSchema()
//        data
        data.orderBy("desc","numClasses").show(false)
//        val gbdt:GBTClassificationModel = null
//        gbdt.transform()
        data.agg(("pv","sum"))
    }
}
