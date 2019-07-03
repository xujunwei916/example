package com.example.others.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestProducer3 {

    public static void main(String[] args) throws Exception {


        // kafka的配置信息
        Properties props = getProperties();
        System.out.println(props);
        //生产者发送消息

        //发送的topic
        String topic = "TestSpeed";
        Producer<String, String> procuder = new KafkaProducer<String, String>(props);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            String value = "aaaaaaaaaaaaaaaaaa"+(i%2) ;//+ i;
            ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, null, value);
            procuder.send(msg);
//            Thread.sleep(10*1000);
//            System.out.println(i);
            if (i % 100000 == 0) {
                System.out.println(i);
            }
        }


        System.out.println("send message over.");
        procuder.close(100, TimeUnit.MILLISECONDS);
        System.out.println(System.currentTimeMillis()-start);

    }

    public static Properties getProperties() {
        Properties props = new Properties();
//        props.put("bootstrap.servers", "172.17.19.65:9092,172.17.19.64:9092,172.17.19.63:9092,172.17.19.51:9092");
        props.put("bootstrap.servers", "172.17.19.51:8001");
        props.put("acks", "1");
        props.put("linger.ms", 1);
        props.put("compression.type", "snappy");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }


}
