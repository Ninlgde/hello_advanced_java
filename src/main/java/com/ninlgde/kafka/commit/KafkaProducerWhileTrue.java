package com.ninlgde.kafka.commit;

import com.ninlgde.kafka.interceptor.ProducerInterceptorPrefix;
import com.ninlgde.kafka.interceptor.ProducerInterceptorPrefixPlus;
import com.ninlgde.kafka.partitioner.DemoPartitioner;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerWhileTrue {
    public static final String brokerList = "localhost:9092,localhost:9093,localhost:9094,localhost:9095";
    public static final String topic = "topic-demo";

    public static Properties initConfig() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, DemoPartitioner.class.getName());
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPrefix.class.getName() + "," + ProducerInterceptorPrefixPlus.class.getName());
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            int i = 0;
            while (true) {
                if (i % 10000 == 0) {
                    Thread.sleep(1000);
                }
                ProducerRecord<String, String> record =
                        new ProducerRecord<>(topic, "msg"+i++);
                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        exception.printStackTrace();
                    } else {
                        System.out.println(metadata.topic() + "-" +
                                metadata.partition() + ":" + metadata.offset());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
