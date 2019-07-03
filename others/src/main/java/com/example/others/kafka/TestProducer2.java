package com.example.others.kafka;

import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestProducer2 {

    public static void main(String[] args) throws Exception {


        // kafka的配置信息
        Properties props = getProperties();
        //生产者发送消息

        //发送的topic
        String topic = "AdControlClick20180911";
        Producer<String, String> procuder = new KafkaProducer<String, String>(props);
        Random random = new Random();

        SimpleDateFormat SSF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        for (int i = 0; i < 100; i++) {
//
//            int send = random.nextInt(100);
//            String t = SSF.format(new Date());
//            //构造message
//            String value = "time=" + t + "&dt=2019-04-17&hr=00&ci=凉山&pr=四川&i=1000480" + send + "&ac=out\ta=" + send + "&o=1&f=100&r=74&od=0&c=36|a=100032&o=0&f=0.0&r=40&od=1";
//            ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, null, value);
//            //发送信息
//            procuder.send(msg);
//
//            Thread.sleep(1);
//        }
        for (int i = 0; i < 100; i++) {
            String value = "time=2019-04-24 13:44:23&dt=2019-04-24&hr=13&ci=大同&pr=山西&i=10004602&ac=click\ta=100046&c=57";
            ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, null, value);
            procuder.send(msg);
        }


        System.out.println("send message over.");
        procuder.close(100, TimeUnit.MILLISECONDS);

    }

    public static Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "172.17.18.116:9092,172.17.18.164:9092");
        props.put("acks", "1");
        props.put("linger.ms", 1);
        props.put("compression.type", "snappy");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }


}
