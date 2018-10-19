package com.example.others.gson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestBean {

    private BigDecimal decimal;

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

//    private Map<String,Object> ob= new HashMap<>();
//
//    public Map<String, Object> getOb() {
//        return ob;
//    }
//
//    public void setOb(Map<String, Object> ob) {
//        this.ob = ob;
//    }
}
