import org.apache.flume.source.kafka.KafkaSourceConstants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestKafkaSub {
    public static void main(String[] args) throws InterruptedException {
        Properties kafkaProps = new Properties();

        kafkaProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                KafkaSourceConstants.DEFAULT_KEY_DESERIALIZER);
        kafkaProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                KafkaSourceConstants.DEFAULT_VALUE_DESERIALIZER);
        //Defaults overridden based on config
        kafkaProps.put("group.id", "test_dcs_group_02");
        kafkaProps.put("auto.offset.reset", "earliest");
        //These always take precedence over config
        kafkaProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "cdh-01:9092,cdh-02:9092,cdh-03:9092");
        kafkaProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
                true);


        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<String, byte[]>(kafkaProps);
        List<String> topics = new ArrayList<>();
        topics.add("test_dcs");
        topics.add("test_dcs_03");

        AtomicBoolean rebalanceFlag = new AtomicBoolean(false);
        consumer.subscribe(topics, new SourceRebalanceListener(rebalanceFlag));
        Iterator<ConsumerRecord<String, byte[]>> it = null;
        ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(10000));
        it = records.iterator();
        consumer.unsubscribe();

        // get next message

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<String>  topics = new ArrayList<>();

                topics.add("test_dcs");
                consumer.subscribe(topics,new SourceRebalanceListener(rebalanceFlag));
                System.out.println("top"+Thread.currentThread().getId() );
            }
        }).start();

//        Thread.sleep(10000L);

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<String>  topics = new ArrayList<>();

                topics.add("test_dcs");
                topics.add("test_dcs_03");
                consumer.subscribe(topics,new SourceRebalanceListener(rebalanceFlag));
                System.out.println("top"+Thread.currentThread().getId() );
            }
        }).start();

//        Thread.sleep(10000L);

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<String>  topics = new ArrayList<>();

                topics.add("test_dcs");
                consumer.subscribe(topics,new SourceRebalanceListener(rebalanceFlag));
                System.out.println("top"+Thread.currentThread().getId() );
            }
        }).start();


        while (it.hasNext()) {
            ConsumerRecord<String, byte[]> message = it.next();
            System.out.println("ConsumerRecord(topic = " + message.topic() + ", partition = " + message.partition() + ", offset = " + message.offset()
                    + ", " + message.timestampType() + " = " + message.timestamp()
                    + ", serialized key size = "  + message.serializedKeySize()
                    + ", serialized value size = " + message.serializedValueSize()
                    + ", headers = " + message.headers()
                    + ", key = " + message.key() + ", value = " + new String(message.value()) + ")");
        }


//        topics = new ArrayList<>();
//        topics.add("test_dcs");
//        topics.add("test_dcs_03");
//
//        consumer.subscribe(topics,new SourceRebalanceListener(rebalanceFlag));
//        consumer.close();


    }


}


class SourceRebalanceListener implements ConsumerRebalanceListener {
    private static final Logger log = LoggerFactory.getLogger(SourceRebalanceListener.class);
    private AtomicBoolean rebalanceFlag;

    public SourceRebalanceListener(AtomicBoolean rebalanceFlag) {
        this.rebalanceFlag = rebalanceFlag;
    }

    // Set a flag that a rebalance has occurred. Then commit already read events to kafka.
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        for (TopicPartition partition : partitions) {
            log.info("topic {} - partition {} revoked.", partition.topic(), partition.partition());
            rebalanceFlag.set(true);
        }
    }

    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        for (TopicPartition partition : partitions) {
            log.info("topic {} - partition {} assigned.", partition.topic(), partition.partition());
        }
    }
}

