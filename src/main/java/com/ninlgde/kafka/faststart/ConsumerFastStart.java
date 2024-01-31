package com.ninlgde.kafka.faststart;

import com.ninlgde.kafka.serialization.Company;
import com.ninlgde.kafka.serialization.CompanyDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerFastStart {
    //    public static final String brokerList = "172.16.0.249:9092";
    public static final String brokerList = "127.0.0.1:30092";
    //    public static final String brokerList = "192.168.3.101:9092";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.deserializer",
                StringDeserializer.class.getName());
        properties.put("value.deserializer",
                CompanyDeserializer.class.getName());
        properties.put("bootstrap.servers", brokerList);
        //设置消费组的名称，具体的释义可以参见第3章
        properties.put("group.id", groupId);
        //创建一个消费者客户端实例
        KafkaConsumer<String, Company> consumer = new KafkaConsumer<>(properties);
        //订阅主题
        consumer.subscribe(Collections.singletonList(topic));
        //循环消费消息
        while (true) {
            ConsumerRecords<String, Company> records =
                    consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, Company> record : records) {
                System.out.println(record.value());
                record.offset();
            }
        }
    }
}
