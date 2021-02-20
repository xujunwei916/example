package com.example.others.java;

public class MathTest {
  static int HALF = 7;

  public static void main(String[] args) {

    System.out.println(ratio(0));
  }

  public static double ratio(double date) {
    return Math.pow(1 / 2D, date / HALF);
  }
}
