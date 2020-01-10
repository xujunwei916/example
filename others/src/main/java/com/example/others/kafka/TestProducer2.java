package com.example.others.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestProducer2 {

    public static void main(String[] args) throws Exception {


        // kafka的配置信息
        Properties props = getProperties();
        //生产者发送消息

        //发送的topic
        String topic = "data_bus_test";
        Producer<String, String> procuder = new KafkaProducer<String, String>(props);
        Random random = new Random();

        SimpleDateFormat SSF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 1000000; i++) {
            String value = "{\"common\":{\"project_name\":\"usercenter\",\"sdk_version\":\"0\",\"app_version\":\"0\",\"channel_id\":\"andbs\",\"ip\":\"0\",\"ip_city\":\"0\",\"pass_id\":\"000003\",\"host_channel_id\":\"UMENG_CHANNEL_VALUE\",\"host_version_name\":\"120100\",\"host_name\":\"2345\\u6d4f\\u89c8\\u5668\",\"host_version_code\":\"\",\"package_name\":\"\",\"device_id\":\"863388035773196\",\"uid\":\"\",\"serial_id\":\"\",\"imei\":\"\",\"mac\":\"\",\"android_id\":\"\",\"access\":\"\",\"phone_num\":\"\",\"nick_name\":\"\",\"main_channel_id\":\"\"},\"data\":{\"event_type\":\"\",\"timeMills\":1575444798996,\"type\":\"\",\"extend\":{\"code\":\"6008\",\"path\":\"\\/clientapi\\/nThAuthLogin\\/bindPhone\"}},\"trackinfo\":{\"server_ip\":\"172.17.18.185\"}}";
            ProducerRecord<String, String> msg = new ProducerRecord<String, String>(topic, null, value);
            procuder.send(msg);
//            Thread.sleep(100L);
            if(i%10000==0){
                System.out.println(i);
            }
        }
        System.out.println(System.currentTimeMillis());

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
