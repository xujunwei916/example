package com.example.flume.filter;

import java.util.Map;

public class Data {

    private String dt;
    private String body;
    private Map<String,String> test_map;

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String,String> getTest_map() {
        return test_map;
    }

    public void setTest_map(Map<String,String> test_map) {
        this.test_map = test_map;
    }

    public Data(String dt, String body, Map<String,String> test_map) {
        this.dt = dt;
        this.body = body;
        this.test_map = test_map;
    }

    public Data() {
    }
}
