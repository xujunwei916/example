package com.example.spark.brodcast;

import java.util.HashMap;
import java.util.Map;

public class Data2 {
    private String name;
    private Integer id;

    private Map<String,Double> map = new HashMap<>();




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Data2(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public Data2() {
    }

    public Map<String, Double> getMap() {
        return map;
    }

    public void setMap(Map<String, Double> map) {
        this.map = map;
    }
}