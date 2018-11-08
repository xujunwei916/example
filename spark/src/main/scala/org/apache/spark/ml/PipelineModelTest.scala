package org.apache.spark.ml

object PipelineModelTest {


    def predictRaw() = {
        val pipelineModel = PipelineModel.load("file:///D:\\test\\model\\2018-11-05")
        val stages = pipelineModel.parent
        println(stages.getClass.getName)
    }

}
