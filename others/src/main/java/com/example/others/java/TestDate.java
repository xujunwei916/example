package com.example.others.java;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TestDate {

  public static void main(String[] args) {
    weekRange();
    monthRange();

  }

  private static void weekRange() {
    DateTimeFormatter TIMESTAMP_DF = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    DateTime dateTime = TIMESTAMP_DF.parseDateTime("2020-05-01 00:00:00");
    StringBuffer sb1 = new StringBuffer();
    StringBuffer sb2 = new StringBuffer();
    sb1.append("case range ");
    sb2.append("case range ");
    for (int i = 1; i <= 17; i++) {
      long start = dateTime.getMillis();
      dateTime = dateTime.plusDays(7);
      long end = dateTime.plusDays(15).getMillis();
      sb1.append("when 'week" + i + "' then " + start + " ");
      sb2.append("when 'week" + i + "' then " + end + " ");
    }
    sb1.append("end");
    sb2.append("end");
    System.out.println(sb1.toString());
    System.out.println(sb2.toString());
  }

  private static void monthRange() {
    DateTimeFormatter TIMESTAMP_DF = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    DateTime dateTime = TIMESTAMP_DF.parseDateTime("2020-05-01 00:00:00");
    StringBuffer sb1 = new StringBuffer();
    StringBuffer sb2 = new StringBuffer();
    sb1.append("case range ");
    sb2.append("case range ");
    for (int i = 1; i <= 4; i++) {
      long start = dateTime.getMillis();
      dateTime = dateTime.plusMonths(1);
      long end = dateTime.plusDays(15).getMillis();
      sb1.append("when 'month" + i + "' then " + start + " ");
      sb2.append("when 'month" + i + "' then " + end + " ");
    }
    sb1.append("end");
    sb2.append("end");
    System.out.println(sb1.toString());
    System.out.println(sb2.toString());
  }

}
