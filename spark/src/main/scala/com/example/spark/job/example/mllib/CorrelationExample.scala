package com.example.spark.job.example.mllib


import com.example.spark.common.SparkJob
import org.apache.spark.ml.linalg.{Matrix, Vectors}
import org.apache.spark.ml.stat.Correlation
import org.apache.spark.sql.Row


object CorrelationExample extends SparkJob{


    override protected def initJob(): Unit = {
        sparkConf.setMaster("local[2]")
        sparkConf.setAppName("test_mllib")
        init()
    }

    override protected def run(args: Array[String]): Unit = {
        val data = Seq(
            Vectors.sparse(4, Seq((0, 1.0), (3, -2.0))),
            Vectors.dense(4.0, 5.0, 0.0, 3.0),
            Vectors.dense(6.0, 7.0, 0.0, 8.0),
            Vectors.sparse(4, Seq((0, 9.0), (3, 1.0))),
            Vectors.sparse(4, Seq((0, 8.0), (3, 1.0)))
        )
//        val data = Seq(
//            Vectors.dense(1.0, 0.0,0.0,-2.0),
//            Vectors.dense(4.0, 5.0, 0.0, 3.0),
//            Vectors.dense(6.0, 7.0, 0.0, 8.0),
//            Vectors.dense(9.0,0.0 ,0.0, 1.0)
//        )


        val sql = sqlContext
        import sql.implicits._

        val df = data.map(Tuple1.apply).toDF("features")
        val Row(coeff1: Matrix) = Correlation.corr(df, "features").head
        println(s"Pearson correlation matrix:\n $coeff1")

        val Row(coeff2: Matrix) = Correlation.corr(df, "features", "spearman").head
        println(s"Spearman correlation matrix:\n $coeff2")
    }


}
