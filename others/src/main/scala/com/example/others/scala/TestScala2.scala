package com.example.others.scala

object TestScala2 {

  def main(args: Array[String]): Unit = {
    val map = Map("aaa"->"1111")

    val testMap = TestMap(map)

    testMap.map1 += ("sss"->"111")
    println(testMap)
    println("P_E_AGE" == new String("P_E_AGE"))


    val ss ="003441da4a2bc15dc35b961834cc77cc,7b821322870ef60972a7fb33fa467zmm,20190708,131072,2020-07-07 00:00:00.0,2020-01-11 00:00:00.0 ,薇姿,Haircare,TMALL,131072,商丘市,257,unknown,36"

  }

}

case class TestMap(var map1:Map[String,String])
