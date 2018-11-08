package com.example.others.scala

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
        val testScala = new TestScala(new C1("c1_1", 100L))

    }
}
