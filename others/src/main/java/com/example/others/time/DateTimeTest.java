package com.example.others.time;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeTest {

  public static void main(String[] args) {
    String date = "2018-08-13 14:02:30";
    DateTimeFormatter nginxDf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    DateTime dateTime = nginxDf.parseDateTime(date);

    for (int i = 0; i < 10; i++) {
      dateTime = dateTime.plusDays(-1);
      System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
    }

    long end = 1000000l;
    long start = System.currentTimeMillis();

    int days = Days
        .daysBetween(new DateTime(start), new DateTime(end))
        .getDays();
    System.out.println(days);

//       long result = nginxDf.parseDateTime(date).getMillis();
//        System.out.println(result);
//        System.out.println(Long.MAX_VALUE);
  }
}
