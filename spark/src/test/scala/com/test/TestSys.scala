package com.test

import org.apache.commons.lang.StringUtils
import org.apache.spark.SparkConf

object TestSys {

    def main(args: Array[String]): Unit = {
//        val sparkConf = new SparkConf().setAppName("aaaa")
////        val a = sparkConf.get("abc")
//        sparkConf.getAll.foreach(println(_))
//        println(sparkConf.get("spark.app.name"))

//        val  map = Map("a"->"b")
//        val  a:String = ""
//        println(Option(a).orElse(Option("aaa")))
//        println("aaa".split(",",2).mkString("---"))
//        println(StringUtils.isNumeric(""))
//        println("12".toInt)

        val requestRegex="""(\w{3,6}) (.+) (.{6,11})""".r
        val s = "GET /js/e/common_houtai.js"
       val result = s match {
            case requestRegex(type1,reqest_pq,version) => (type1,reqest_pq,version)
            case i:String =>(("UNKOWN",i,"UNKOWN"))
        }
        println(result)
    }
}
