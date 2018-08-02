package com.example.others.gson

import org.json4s.JsonDSL.WithBigDecimal._
import org.json4s.jackson.Serialization
import org.json4s.{JsonAST, ShortTypeHints}
import org.json4s.jackson.JsonMethods._
object TestJson4j {
    def main(args: Array[String]): Unit = {
        val map = (0 to 23).map(x => ("%02d".format(x) -> x.toString))

        implicit val formats = Serialization.formats(ShortTypeHints(List()))
       val str :JsonAST.JObject = map.aggregate(JsonAST.JObject())(
           (A,B) => A~B,
           (A,B) => A~B
       )
        val s=  compact(render(str))
        println(s)
//        val xs = "{\"A0350\":{\"zip\":\"HaoZip.zip\",\"un_reg\":\"1\"}}"
//        implicit val formats = DefaultFormats
//        val jsonMap = JsonMethods.parse(xs).extract[util.HashMap[_, _]]
    }

}
