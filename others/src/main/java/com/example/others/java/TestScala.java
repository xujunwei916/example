package com.example.others.java;

import java.util.ArrayList;
import java.util.List;

import scala.Function1;
import scala.collection.JavaConverters;
import scala.collection.Seq;

public class TestScala {
    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("b");
        Seq<String> seq = JavaConverters.asScalaIteratorConverter(list.iterator()).asScala().toSeq();




    }
}
