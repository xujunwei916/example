package com.example.others.test;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

public class TestHbaseStr {
  /**
   * HBASE中文转换
   */
  @Test
  public void testHbaseStr() throws Exception {
//        Hbase UTF8编码
    String content = "\\xE8\\x90\\x8C\\xE8\\x90\\x8C4|";
    char[] chars = content.toCharArray();
    StringBuffer sb = new StringBuffer();
    for (int i = 2; i < chars.length; i = i + 4) {
//            System.out.println(chars[i]);
      sb.append(chars[i]);
//            System.out.println(chars[i + 1]);
      sb.append(chars[i + 1]);
    }
    System.out.println(sb);
    String ouputStr = new String(Hex.decodeHex(sb.toString().toCharArray()), "UTF-8");
    System.out.println(ouputStr);
  }
}
