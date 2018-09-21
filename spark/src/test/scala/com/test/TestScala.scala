package com.test

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
        val a =Map("a"->1,"b"->2)
//        println(a.pu)


    }
}
