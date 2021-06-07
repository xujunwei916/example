package com.example.others.scala

import scala.xml.NodeBuffer

class ScalaHtml {
  def plugins: NodeBuffer = {
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.20.1/css/theme.default.min.css" type="text/css"/>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.20.1/js/jquery.tablesorter.min.js"></script>
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" type="text/css"/>
      <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
      <script type="text/javascript">
        {xml.Unparsed("""$(document).ready(function() {$(".tablesorter").tablesorter();});""")}
      </script>
  }


}
object ScalaHtml {
  def main(args: Array[String]): Unit = {
    val aa = new ScalaHtml().plugins
    println(aa)
  }
}
