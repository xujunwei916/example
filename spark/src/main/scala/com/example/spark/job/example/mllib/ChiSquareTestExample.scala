package com.example.spark.job.example.mllib

import com.example.spark.common.SparkJob
import org.apache.spark.ml.linalg.{Vector, Vectors}
import org.apache.spark.ml.stat.ChiSquareTest

object ChiSquareTestExample extends SparkJob {

    override protected def initJob(): Unit = {
        sparkConf.setMaster("local[2]")
        sparkConf.setAppName("test_mllib2")
        init()
    }
    def run(args: Array[String]): Unit = {
        val data = Seq(
            (0.0, Vectors.dense(0.5, 10.0)),
            (0.0, Vectors.dense(1.5, 20.0)),
            (1.0, Vectors.dense(1.5, 30.0)),
            (0.0, Vectors.dense(3.5, 30.0)),
            (0.0, Vectors.dense(3.5, 40.0)),
            (1.0, Vectors.dense(3.5, 40.0))
        )

        val sql = sqlContext
        import sql.implicits._

        val df = data.toDF("label", "features")
        val chi = ChiSquareTest.test(df, "features", "label").head
        println(s"pValues = ${chi.getAs[Vector](0)}")
        println(s"degreesOfFreedom ${chi.getSeq[Int](1).mkString("[", ",", "]")}")
        println(s"statistics ${chi.getAs[Vector](2)}")
    }
}
