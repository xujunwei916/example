package com.test

import shapeless.ops.zipper.First

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

        println(f1(_ * 6))

        println(f2(5)(7))
        val f= f3(8)(_)
        println(f(7))
        println(f(6))


    }
}
