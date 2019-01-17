package com.test

class TestUpdate {

    var a = 0
    var b = 0

    def update(add: Int, ad2: Int) = {
        a += add

        b += ad2
    }

    def apply: TestUpdate = new TestUpdate()


}

object TestUpdate {
    def main(args: Array[String]): Unit = {
        val test = new TestUpdate
        test(3) = 8
        //        test.update(2)
        println(test.a)
                println(test.b)
    }
}
