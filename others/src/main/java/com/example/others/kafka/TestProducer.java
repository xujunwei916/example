package com.example.others.kafka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;

public class TestProducer {

    public static void main(String[] args) throws Exception {

        List<Message> messages=readMessage("D:\\test\\swool\\swoole-kafka-1-2018-10-15_08.log");

        Properties props = new Properties();
        props.put("bootstrap.servers", "172.17.20.119:9092,172.17.20.1137:9092");
        props.put("acks", "1");
        props.put("linger.ms", 1);
        props.put("compression.type", "snappy");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //生产者发送消息

        String topic = "intAds20181019";
        Producer<String, String> procuder = new KafkaProducer<String, String>(props);
        for (Message message:messages) {

            if("intAds20180629".equals(message.getTopic())){
//                System.out.println();
                ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, message.getKey(),message.getValue());
                procuder.send(msg);
            }else{
//                System.out.println(message.getTopic());
            }

        }
        System.out.println("send message over.");
        procuder.close(100, TimeUnit.MILLISECONDS);

    }


    public static List<Message> readMessage(String path) throws Exception {
        List<Message> messages = new ArrayList<>();
        List<String> lines = IOUtils.readLines(new BufferedReader(new FileReader(path)));
        for (String line : lines) {
            String[] fields = line.split("#", 3);
            if (fields == null || fields.length != 3) {
                System.out.println(line);
            } else {
                String topic = fields[0].substring(20);
                String key = fields[1];
                String value = fields[2];
                Message message = new Message(topic, key, value);
                messages.add(message);

            }
        }


        return messages;
    }
}

class Message {
    private String topic;
    private String key;
    private String value;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "topic='" + topic + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Message(String topic, String key, String value) {
        this.topic = topic;
        this.key = key;
        this.value = value;
    }
}