package com.example.others.gson;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.HashSet;
import java.util.Set;

public class TestTable {

    public static void main(String[] args) {
        Table<String,String,Long> table = HashBasedTable.create();



        String json = JsonHelper.toJson(table.cellSet());
        System.out.println(json);


    }
}
