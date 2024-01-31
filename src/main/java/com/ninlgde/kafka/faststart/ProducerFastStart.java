package com.ninlgde.kafka.faststart;

import com.ninlgde.kafka.serialization.Company;
import com.ninlgde.kafka.serialization.CompanySerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerFastStart {
    //    public static final String brokerList = "172.16.0.249:9092";
    public static final String brokerList = "127.0.0.1:30092";
    //    public static final String brokerList = "192.168.3.101:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.serializer",
                StringSerializer.class.getName());
        properties.put("value.serializer",
                CompanySerializer.class.getName());
        properties.put("bootstrap.servers", brokerList);


        KafkaProducer<String, Company> producer =
                new KafkaProducer<>(properties);
        Company a = new Company("ninlgde", "beijing");
        ProducerRecord<String, Company> record =
                new ProducerRecord<>(topic, a);
        try {
            producer.send(record, (metadata, exception) -> {
                System.out.println(metadata);
                if (exception != null) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}
