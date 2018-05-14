package com.example.others.ansj;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.ToAnalysis;


public class AnsjLanguage {
    public static void main(String[] args) {

       Result result= ToAnalysis.parse("你好，我是一个中国人");
        System.out.println(result.getTerms());


    }
}
