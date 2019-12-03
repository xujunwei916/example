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
        String topic = "test_dcs_03";
        Producer<String, String> procuder = new KafkaProducer<String, String>(props);
        Random random = new Random();

        SimpleDateFormat SSF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < 1000; i++) {
            String value = "{\"trackinfo\":{}}";
            ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, null, value);
            procuder.send(msg);
            Thread.sleep(100L);
        }


        System.out.println("send message over.");
        procuder.close(100, TimeUnit.MILLISECONDS);

    }

    public static Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "cdh-01:9092");
        props.put("acks", "1");
        props.put("linger.ms", 1);
        props.put("compression.type", "snappy");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }


}
