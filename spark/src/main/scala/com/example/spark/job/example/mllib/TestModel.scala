package com.example.spark.job.example.mllib

import org.apache.spark.ml.linalg.Vectors

object TestModel {
    def main(args: Array[String]): Unit = {
        val data = Seq(
            Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
            Vectors.dense(4.0, 5.0, 0.0, 3.0),
            Vectors.dense(6.0, 7.0, 0.0, 8.0),
            Vectors.sparse(4, Seq((0, 9.0), (3, 1.0)))
        )
        data.foreach(println(_))
    }
}
