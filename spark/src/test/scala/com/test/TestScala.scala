package com.test

import java.io.{BufferedReader, FileReader}

import shapeless.ops.zipper.First

import scala.collection.mutable

object TestScala {


    def f1(f2: (Int) => Int) = {
        f2(5)
    }

    def f2(first: Int) = {
        (y: Int) => y * first
    }


    def f3(first: Int)(second: Int) = {
        first * second
    }


    def main(args: Array[String]): Unit = {

        //        println(f1(_ * 6))
        //
        //        println(f2(5)(7))
        //        val f= f3(8)(_)
        //        println(f(7))
        //        println(f(6))


        //        val mapResult = new mutable.HashMap[String, String]
        //        val s = mapResult.getOrElseUpdate("aaaa","bbbbbb")
        //        println(mapResult)

        //        val a= Array(1,2,3,4,5,6,7,10,8,9)
        //        val b = a.take(6).toSet[Int]
        //        println(a.mkString(","))
        //        println("^\\d{1,2}$".r.findFirstIn(date).isEmpty)
        //        val a =Map("a"->1,"b"->2)
        //        println(a.pu)
        //        val ss= s"$$"
        //        println(ss)
        //
        //        val date="2018-11-11"
        //        val sql =
        //            s"""
        //               |select
        //               |date,
        //               |sum(if(task_id=='1001',count1,0)) `开启奖励下发数`,
        //               |sum(if(task_id=='1002',count1,0)) `拉活奖励下发数`,
        //               |sum(if(task_id=='1001',1,0)) `开启奖励下发数passid去重`,
        //               |sum(if(task_id=='1002',1,0)) `拉活奖励下发数passid去重`
        //               |from (
        //               |select date,task_id,count1,passid
        //               |from
        //               |(
        //               |select key["task_id"] task_id ,key["passid"] passid ,date,count(1) count1 from interactive_ads_v3  where  typ='13'
        //               |and date >='$date' and date<='$date'
        //               |group by task_id,passid,date
        //               |)
        //               |)
        //               |group by date
        //            """.stripMargin
        //        println(sql)

        //        val read = new BufferedReader(new FileReader("D://test//test.txt"))
        //        var line:String = null
        //        while ( {
        //            line = read.readLine
        //            line!=null
        //        }) {
        //            println(line)
        //        }
        //        read.close()
        //        identity
        //        println(line = null)
        //        read.close()


        "8|13|10|2|||".split("\\|", -1).foreach(i => println( "|" + i + "|"))

    }
}
