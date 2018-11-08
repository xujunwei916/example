package com.example.spark.job.example.mllib

import org.apache.spark.ml.linalg.Vector
import org.apache.spark.sql.{SaveMode, SparkSession}

object TestModel {
    def main(args: Array[String]): Unit = {
        //        val data = Seq(
        //            Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
        //            Vectors.dense(4.0, 5.0, 0.0, 3.0),
        //            Vectors.dense(6.0, 7.0, 0.0, 8.0),
        //            Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
        //        )
        //        data.foreach(println(_))
        val spark = SparkSession.builder().getOrCreate()
        val result = spark.sql("")
        val pipelineModelBC = spark.sparkContext.broadcast(null)

        //        val pipelineModelBC= spark.sparkContext.broadcast(stage6)
        import spark.implicits._
        import org.apache.spark.ml.linalg.Vector
        result.select("features", "uid", "ggid").rdd.mapPartitions(sign => {
            val predictRawMethod = pipelineModelBC.value.getClass.getMethod("predictRaw", classOf[Vector])
            val raw2probabilityMethod = pipelineModelBC.value.getClass.getMethod("raw2probabilityInPlace", classOf[Vector])
            sign.map(row => {
                val features = row.getAs[Vector]("features")
                val uid = row.getAs[String]("uid")
                val ggid = row.getAs[Int]("ggid")
                val predictRaw = predictRawMethod.invoke(pipelineModelBC.value, features).asInstanceOf[Vector]
                val raw2probability = (raw2probabilityMethod.invoke(pipelineModelBC.value, predictRaw).asInstanceOf[Vector])
                (uid, ggid, raw2probability)
            })
        }).toDF("uid", "ggid", "probability").write.mode(SaveMode.Overwrite).orc("/guess.union2.50bang.org/data_result_2/ddd4")


    }
}
