package com.example.others.test

import java.io.IOException


object Tuple2Csv {


    val INITIAL_STRING_SIZE = 128
    val DEFAULT_ESCAPE_CHARACTER = '"'
    val DEFAULT_SEPARATOR = ','
    val DEFAULT_QUOTE_CHARACTER = '"'
    val NO_QUOTE_CHARACTER = '\u0000'
    val NO_ESCAPE_CHARACTER = '\u0000'
    val DEFAULT_LINE_END = "\n"


    def main(args: Array[String]): Unit = {
        System.out.println(tuple2Line(("111111", "nnnn", "\"你好")))
    }


    def tuple2Line(product: Product) = {
        toLine2(product.productIterator.map(_.asInstanceOf[String]).toArray)
    }

    def toLine2(nextLine: Array[String]): String = {
        if (nextLine == null) return null
        val sb = new StringBuilder(INITIAL_STRING_SIZE)
        (0 until nextLine.length).foreach(i => {
            if (i != 0) sb.append(DEFAULT_SEPARATOR)
            val nextElement = nextLine(i)
            if (nextElement != null) {
                if (DEFAULT_QUOTE_CHARACTER != NO_QUOTE_CHARACTER) sb.append(DEFAULT_QUOTE_CHARACTER)
                sb.append(if (stringContainsSpecialCharacters(nextElement)) processLine(nextElement)
                else nextElement)
                if (DEFAULT_QUOTE_CHARACTER != NO_QUOTE_CHARACTER) sb.append(DEFAULT_QUOTE_CHARACTER)
            }
        })
        sb.toString
    }

    private def stringContainsSpecialCharacters(line: String) = {
        line.indexOf(DEFAULT_QUOTE_CHARACTER) != -1 || line.indexOf(DEFAULT_ESCAPE_CHARACTER) != -1
    }

    protected def processLine(nextElement: String): StringBuilder = {
        val sb = new StringBuilder(INITIAL_STRING_SIZE)
        (0 until nextElement.length).foreach(j => {
            val nextChar = nextElement.charAt(j)
            if (DEFAULT_ESCAPE_CHARACTER != NO_ESCAPE_CHARACTER && nextChar == DEFAULT_QUOTE_CHARACTER) sb.append(DEFAULT_ESCAPE_CHARACTER).append(nextChar)
            else if (DEFAULT_ESCAPE_CHARACTER != NO_ESCAPE_CHARACTER && nextChar == DEFAULT_ESCAPE_CHARACTER) sb.append(DEFAULT_ESCAPE_CHARACTER).append(nextChar)
            else sb.append(nextChar)

        })
        sb
    }
}
