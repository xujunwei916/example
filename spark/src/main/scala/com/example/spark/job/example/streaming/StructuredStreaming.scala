
package com.example.spark.job.example.streaming

import org.apache.spark
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by dongyunlong on 2017/7/13.
  */
object StructuredStreaming {
    def main(args: Array[String]) {
        val spark = SparkSession
            .builder()
            .config(new SparkConf().setMaster("local[2]"))
            .appName(getClass.getName)
            .getOrCreate()
        import spark.implicits._
        val lines = spark.readStream
            .format("socket")
            .option("host", "localhost")
            .option("port", 9999)
            .load()
        // Split the lines into words
        val words = lines.as[String].flatMap(_.split(" "))
        // Generate running word count
        val wordCounts = words.groupBy("value").count()
        // Start running the query that prints the running counts to the console
        val query = wordCounts.writeStream
            .outputMode("complete")
            .format("console")
            .start()
        query.awaitTermination()
    }
}