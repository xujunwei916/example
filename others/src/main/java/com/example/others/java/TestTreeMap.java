package com.example.others.java;

import com.google.common.primitives.UnsignedLongs;
import java.util.TreeMap;

public class TestTreeMap {
  public static String x36(long l) {
    return UnsignedLongs.toString(l, 36);
  }

  public static void main(String[] args) {
    TreeMap<Long,String> path = new TreeMap<>();

    path.put(1L,"1");
    path.put(8L,"8");
    path.put(4L,"4");
    path.put(5L,"5");
    path.put(12000L,"12000");
    path.put(1299900L,"1299900L");
    path.put(22990L,"22990L");
    System.out.println(path);
    for (int i=0;i<2000;i+=6) {
      System.out.println(x36(i));
    }
    System.out.println(x36(1552));

  }
}
