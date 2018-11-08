package com.example.spark.job.example.mllib

import com.example.spark.common.SparkJob
import org.apache.spark.ml.{PipelineModel, PipelineModelTest}

object TestModelPredict extends SparkJob{
    def run(args: Array[String]): Unit = {
        PipelineModelTest.predictRaw()
    }
}
