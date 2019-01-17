package org.apache.spark.ml

object PipelineModelTest {

    val pipelineModel = PipelineModel.load("file:///D:\\test\\model\\2018-11-05")
    def predictRaw() = {
//        pipelineModel.transform()
        val stages = pipelineModel.stages
        stages.foreach(stage => println(stage.getClass.getName))
    }

}
