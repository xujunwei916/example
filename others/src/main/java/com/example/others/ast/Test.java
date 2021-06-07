package com.example.others.ast;

import org.roaringbitmap.RoaringBitmap;

public class Test {

  public static void main(String[] args) {
    RoaringBitmap bitmap = new RoaringBitmap();
    bitmap.add(1);
    bitmap.add(100);
    RoaringBitmap bitmap2 = new RoaringBitmap();
    bitmap2.add(100);
    bitmap.andNot(bitmap2);
    System.out.println(bitmap.getCardinality());
  }
}
