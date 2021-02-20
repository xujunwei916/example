package com.example.others.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Gauge.Child;
import io.prometheus.client.exporter.PushGateway;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;


public class PushgwGauge {


  public static final Map<String,Double> maps = new HashMap<>();
  // 定义 PushGateway 实例
  private static final PushGateway prometheusPush = new PushGateway("pushgw-test.hypers.cc");


  // 定义 key的维度标签名
//    static final Gauge gaugeDemo = Gauge.build()
  static final Gauge gaugeDemo = Gauge.build()
      .name("hypers_test_yum")
      .labelNames("day","usertype")
      .help("counter 类型")
      .register();

  //测试发送
  public static void push(String[] labelValues, double value,Map<String,Double> map) {
    String key = StringUtils.join(labelValues,"_");
    Double oldValue=map.get(key);
    if(oldValue!=null && oldValue> value){
      value=oldValue;
    }else{
      map.put(key,value);
    }

    gaugeDemo.labels(labelValues).set(value);

    try {
      prometheusPush.push(gaugeDemo, "hypers_job2");
    } catch (IOException e) {
      e.printStackTrace();

    }

  }


  public static void main(String[] args) {

    String[] types = {"001", "001", "001", "002", "003", "004"};

    while (true) {

      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String formatDate = sdf.format(date);
      String day = formatDate.split(" ")[0];
      String type = types[new Random().nextInt(6)];
      String[] labelValues = {day, type};
      double value = new Random().nextInt(20);

      push(labelValues, value,maps);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
}
