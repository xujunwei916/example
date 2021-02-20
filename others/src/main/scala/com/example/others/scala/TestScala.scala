package com.example.others.scala

import java.util

import org.apache.commons.collections.ComparatorUtils

class TestScala(private val c1: C1) {
    c1.c1Print()

}

class C1(val name: String, val id: Long) {

    def c1Print() {
        println(name)
        println(id)
    }
}

object TestScala {
    def main(args: Array[String]): Unit = {
////        val testScala = new TestScala(new C1("c1_1", 100L))
//        val list :java.util.List[String] =new util.ArrayList[String]()
//        list.add("sssss")
//        list.add(null)
//        val List(k1,null) = list
////        println(k1)
////        println(k2)
//        Array(1,2,3).mkString(",")

      val a = List(("a",10),("b",10),("a",11),("a",9))

      println(a.sorted)
      println(a.sortWith((i1,i2) => i1._1 < i2._1 || i1._1 ==i2._1 && i1._2<i2._2)  )
    }
}
