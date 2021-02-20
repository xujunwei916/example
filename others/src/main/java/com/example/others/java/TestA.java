package com.example.others.java;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class TestA {


  @Accessors
  private String a;
  private String b;

  public static void main(String[] args) {
    TestA  testA= new TestA();
  }
}



