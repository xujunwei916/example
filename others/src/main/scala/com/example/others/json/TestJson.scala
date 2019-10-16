package com.example.others.json


import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods
import java._

import org.roaringbitmap.RoaringBitmap

import scala.collection.mutable

object TestJson {


    def main(args: Array[String]): Unit = {
        //        val xs = "{\"A0350\":{\"zip\":\"HaoZip.zip\",\"un_reg\":\"1\"}}"
        //        implicit val formats = DefaultFormats
        //        val jsonMap =JsonMethods. parse(xs).extract[util.HashMap[_,_]]
        //
        //
        //        println(jsonMap)
//        val roaringBitmap = new RoaringBitmap()
//        var i = 1L
//        while (i < 2415919103L) {
//            roaringBitmap.add(i.toInt)
//            i += 1
//            if(i%1000000==0)
//                println(i)
//        }
//
////        (1L until 2415919103L).foreach(i => roaringBitmap.add(i.toInt))
//        println(roaringBitmap.getCardinality)
//        println(roaringBitmap.getCardinality & 0xffffffffL)
        //        var count =0
        //        (0 until 255).foreach(i1 => {
        //            (99 until 102).foreach(i2 => {
        //                (8 until 25).foreach(i3 => {
        //                    (0 until 50).foreach(i4 => {
        //                        val ip = i1  + "." + i2 + "." + i3  + "." + i4
        ////                        println(ip + " = "+ ipToLong(ip))
        //                        roaringBitmap.add( ipToLong(ip))
        //                        count += 1
        //                    })
        //                })
        //            })
        //        })
        //        println(count)
        //        println(roaringBitmap.getCardinality)
        //        println(roaringBitmap.toArray.size)
        //        println(-1&0xffffffffL)
//        println(0x8fffffffL)


//        val  map = new mutable.HashMap[String,String]
//
//        new Thread(new Runnable {
//            override def run(): Unit = {
//
//                map.put("A","A")
//
//
//            }
//        }).start()
//        val a =new A(0)
//
//        for (i<-1 to 10)
//            {
//                a.add()
//            }
//        println(a)
//        val map  = new mutable.HashMap[Int,Int]()
//        (1 to 1000).map( i=> map += (i -> i) )
//        println(map.size)
//
//        map.foreach(i=> {
//            if(i._1 % 2 == 0){
//                map.remove(i._1)
//            }
//        })
//
//        println(map.size)



//        val a:Double = 1000.0
//        val b =a.asInstanceOf[Long]
//        println("s"+b+"b")


//        val a = Array(1,2,3,4,5,6,7)
//        val b = a.grouped(3).foreach(a => println(a.mkString("sss{",",","}ddd")))
//        println(b)

//        new



    }

//    def ipToLong(ip: String): Int = {
//        val ipDot = ip.split("\\.")
//        if (ipDot.length != 4) 0
//        else {
//            var result = 0l
//            ipDot.indices.foreach(i => {
//                result |= (Integer.valueOf(ipDot(i)) & 0xffL) << (ipDot.length - 1 - i) * 8
//            })
//            result.toInt
//        }
//    }
}
//case class A(var value:Int){
//    def  add (): Unit ={
//        value+=1
//    }
//}
