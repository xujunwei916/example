import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConsumerTest {
 
    public static void main(String[] args) {
        final ConcurrentLinkedQueue<String> subscribedTopics = new ConcurrentLinkedQueue<>();
 
        // 创建另一个测试线程，启动后首先暂停10秒然后变更topic订阅
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // swallow it.
                }
                // 变更为订阅topic： btopic， ctopic
                subscribedTopics.addAll(Arrays.asList("btopic", "ctopic"));
            }
        };
        new Thread(runnable).start();
 
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "my-group1");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
 
        // 最开始的订阅列表：atopic、btopic
        consumer.subscribe(Arrays.asList("atopic", "btopic"));
        while (true) {
            consumer.poll(2000); //表示每2秒consumer就有机会去轮询一下订阅状态是否需要变更
            // 本例不关注消息消费，因此每次只是打印订阅结果！
            System.out.println(consumer.subscription());
            if (!subscribedTopics.isEmpty()) {
                Iterator<String> iter = subscribedTopics.iterator();
                List<String> topics = new ArrayList<>();
                while (iter.hasNext()) {
                    topics.add(iter.next());
                }
                subscribedTopics.clear();
                consumer.subscribe(topics); // 重新订阅topic
            }
        }
        // 本例只是测试之用，使用了while(true)，所以这里没有显式关闭consumer
//        consumer.close();
    }
}