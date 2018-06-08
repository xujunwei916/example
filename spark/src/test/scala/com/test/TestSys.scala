package com.test

import org.apache.commons.lang.StringUtils
import org.apache.spark.SparkConf

object TestSys {

    def main(args: Array[String]): Unit = {
//        val sparkConf = new SparkConf().setAppName("aaaa")
////        val a = sparkConf.get("abc")
//        sparkConf.getAll.foreach(println(_))
//        println(sparkConf.get("spark.app.name"))

        val  map = Map("a"->"b")
        val  a:String = ""
        println(Option(a).orElse(Option("aaa")))
        println("aaa".split(",",2).mkString("---"))
        println(StringUtils.isNumeric(""))
        println("12".toInt)
    }
}
