package com.example.others.text;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TestSystemOut {
  public static void main(String[] args) {
    BufferedReader br = null;
    BufferedWriter bw = null;
    try {
      // 利用转换流将控制台上的字节流转换成字符流
      br = new BufferedReader(new InputStreamReader(System.in));
      // 利用转换流将缓冲区中的字符流转成控制台上的字符流

//      bw = new BufferedWriter(new OutputStreamWriter(System.out));
      CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new OutputStreamWriter(System.out, "utf-8")) );
      // 单个字符的循环输入输出
//      oneWordIO(br, bw);
      // 一行一行循环输入输出
      oneLineIO(br, csvWriter);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bw.close();
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  private static void oneLineIO(BufferedReader in, CSVWriter out)
      throws IOException {
    String str = null;
    while ((str = in.readLine()) != null) {

      out.flush();
    }
  }

  /**
   * 单个字符的输入整行输出
   */
  private static void oneWordIO(BufferedReader in, BufferedWriter out)
      throws IOException {
    int ch = -1;
    while ((ch = in.read()) != -1) {
      out.write(ch);
      out.flush();
    }
  }


}
