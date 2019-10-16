package com.example.spark.bean;

import com.example.spark.brodcast.Data;

import java.util.Arrays;
import java.util.List;

public class TestCreateList {

    public static List<Data> getList() {
        return Arrays.asList(new Data[]{new Data("a",1), new Data("b",2),new Data("c",3)});
    }
}
