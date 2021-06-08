package com.ninlgde.kafka.analysis;

import com.ninlgde.kafka.interceptor.ProducerInterceptorPrefix;
import com.ninlgde.kafka.interceptor.ProducerInterceptorPrefixPlus;
import com.ninlgde.kafka.partitioner.DemoPartitioner;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerAnalysis {
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
//            ProducerRecord<String, String> record =
//                    new ProducerRecord<>(topic, "Hello, Kafka!");
//            producer.send(record).get();
//            Future<RecordMetadata> future = producer.send(record);
//            RecordMetadata metadata = future.get();
//            System.out.println(metadata.topic() + "-" +
//                    metadata.partition() + ":" + metadata.offset());
//            producer.send(record, new Callback() {
//                @Override
//                public void onCompletion(RecordMetadata metadata, Exception e) {
//                    if (e != null) {
//                        e.printStackTrace();
//                    } else {
//                        System.out.println(metadata.topic() + "-" +
//                                metadata.partition() + ":" + metadata.offset());
//                    }
//                }
//            });
            int i = 0;
            while (i < 1000) {
//                if (i % 10 == 9) {
//                    Thread.sleep(1000);
//                }
                ProducerRecord<String, String> record =
                        new ProducerRecord<>(topic, "msg"+i++);
//                try {
//                    producer.send(record).get();
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
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
