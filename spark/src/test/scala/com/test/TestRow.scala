package com.test

import java.io

import org.apache.spark.sql.RowFactory

import scala.reflect.io.File

object TestRow {


    def main(args: Array[String]): Unit = {
//        (0 to 1000).foreach(
//            i => {
//                val row = RowFactory.create("1000" , i.asInstanceOf[AnyRef])
//                println(row.hashCode())
//            }
//        )


        val file = File("D://tmp")
        file.toDirectory.files.foreach(println(_))

        println("_______")
        val file2 = new io.File("D://tmp")
        val files= file2.listFiles()
        files.foreach(println(_))


    }

}
