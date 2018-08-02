package com.example.others.test

import java.io.{FileReader, FileWriter}
import java.security.MessageDigest

import org.apache.commons.io.IOUtils

import scala.collection.mutable.{HashSet, ListBuffer}

object TestJson {
    val digestMd5: MessageDigest = MessageDigest.getInstance("MD5")

    def main(args: Array[String]): Unit = {

        //        val s= "{\"data\":{\"date\":\"2018-04-26\",\"installedAppList\":\"[{\\\"appName\\\":\\\"5.4.0.66_r807534\\\",\\\"package\\\":\\\"com.tencent.mm\\\",\\\"version\\\":\\\"480\\\"},{\\\"appName\\\":\\\"5.3.3\\\",\\\"package\\\":\\\"com.kingroot.kinguser\\\",\\\"version\\\":\\\"193\\\"},{\\\"appName\\\":\\\"1.0\\\",\\\"package\\\":\\\"ads.mobile2345.com.mobileadsplatform2\\\",\\\"version\\\":\\\"1\\\"},{\\\"appName\\\":\\\"1.0\\\",\\\"package\\\":\\\"ads.mobile2345.com.mobileadsplatform1\\\",\\\"version\\\":\\\"1\\\"},{\\\"appName\\\":\\\"3.1\\\",\\\"package\\\":\\\"com.jumobile.apk.mgr\\\",\\\"version\\\":\\\"301\\\"},{\\\"appName\\\":\\\"3.18.1100(SL)_VER_32526089295372\\\",\\\"package\\\":\\\"com.qiku.powermaster\\\",\\\"version\\\":\\\"318\\\"},{\\\"appName\\\":\\\"4.7.7\\\",\\\"package\\\":\\\"com.speedsoftware.rootexplorer\\\",\\\"version\\\":\\\"999477\\\"}]\",\"runningAppList\":\"[{\\\"appName\\\":\\\"4.7.7\\\",\\\"package\\\":\\\"com.speedsoftware.rootexplorer\\\",\\\"version\\\":\\\"999477\\\"},{\\\"appName\\\":\\\"5.3.3\\\",\\\"package\\\":\\\"com.kingroot.kinguser\\\",\\\"version\\\":\\\"193\\\"},{\\\"appName\\\":\\\"1.0\\\",\\\"package\\\":\\\"ads.mobile2345.com.mobileadsplatform1\\\",\\\"version\\\":\\\"1\\\"}]\"},\"device\":{\"orientation\":1,\"os\":\"Android\",\"model\":\"Coolpad 8675-FHD\",\"height\":1920,\"osv\":\"4.4.4\",\"deviceType\":1,\"width\":1080,\"imei\":\"866394021855143\",\"brand\":\"Coolpad\",\"operator\":0,\"network\":1},\"app\":{\"appid\":0,\"versionName\":\"1.0\",\"packageName\":\"ads.mobile2345.com.mobileadsplatform1\",\"channel\":\"cu1000490\",\"version\":\"1\"},\"adsenseid\":-1,\"user\":{\"uid\":\"96d99a5f1c6eee339414c74560f03cac\"}}";
        //        val objectMapper =new ObjectMapper()
        //        val result = objectMapper.readValue(s,classOf[JsonBean])
        //        println(result.data.date)


        //        val set =Set("5","4","2").map(_.toInt)
        //
        //        val b = set.toArray.sorted

        val array = Array(0, 0, 0, 0, 0, 0, 0, 0, 0)


        val list = (0 to 510).map(i => {
            arrayAdd(array)
            array.mkString("")
        }
        )


        println(list)


        //        val aaa = "test11111"
        //        val bbb = "test22222"
        //
        //
        //
        //        for (i<-0 to 1000){
        //            new Thread(new Runnable {
        //                override def run(): Unit = {
        //                    val result1=  md5(aaa);
        //                    println(" result1 = "+result1)
        //                }
        //            }).start()
        //            new Thread(new Runnable {
        //                override def run(): Unit = {
        //                    val result2=  md5(bbb);
        //                    println(" result2 = "+result2)
        //                }
        //            }).start()
        //        }

        import scala.collection.JavaConversions._
        val line = IOUtils.readLines(new FileReader("d://tmp//lebie2.txt")).map(_.toString)
        val set = new HashSet[(String, String)]()

        val result = line.map(_.split(",")).map(i => {
            set.add((i(0), convert(i(1))))
            Array(i(0), convert(i(1)), i(2), i(3))
        })
            .map(_.mkString(",")).toList



        val tmp2 =new ListBuffer[Array[String]]()
        list.foreach(i => {

            if(!set.contains(("2018-07-27",i))){
                println(("2018-07-27",i))
                tmp2.add(Array("2018-07-27",i,"0","0"))
            }


        })
        list.foreach(i => {

            if(!set.contains(("2018-07-28",i))){
                println(("2018-07-28",i))
                tmp2.add(Array("2018-07-28",i,"0","0"))
            }

        })

        val writer = new FileWriter("d://tmp//lebie_tmp.txt")
        IOUtils.writeLines(result++tmp2.map(_.mkString(",")), null, writer)
        writer.close()


    }

    def convert(ss: String) = {
        val array = Array(0, 0, 0, 0, 0, 0, 0, 0, 0)

        ss.foreach(i => array((i + "").toInt - 1) = 1)
        array.mkString("")

    }

    def arrayAdd(array: Array[Int]) = {

        var tmp = 1
        (0 until 9).foreach(i => {
            if (tmp == 0) {}
            else {
                if (array(i) == 1) {
                    array(i) = 0
                    tmp = 1
                } else {
                    array(i) = 1
                    tmp = 0
                }
            }
        }
        )


    }


    def md5(text: String): String =
        MessageDigest.getInstance("MD5").digest(text.getBytes()).map(0xFF & _).map {
            "%02x".format(_)
        }.foldLeft("") {
            _ + _
        }


}
