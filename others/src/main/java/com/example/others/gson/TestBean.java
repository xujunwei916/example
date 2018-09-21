package com.example.others.gson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestBean {
    private Integer id;
    private Map<String, String> client;
    private String data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, String> getClient() {
        return client;
    }

//    public void setClient(Map<String, String> client) {
//        this.client = client;
//    }

    public void setClient(String clientStr) {
        this.client = JsonHelper.json2Object(clientStr, Map.class);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", client=" + client +
                ", data='" + data + '\'' +
                '}';
    }
}
