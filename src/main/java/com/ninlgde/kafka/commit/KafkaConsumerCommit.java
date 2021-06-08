package com.ninlgde.kafka.commit;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaConsumerCommit {
    public static final String brokerList = "localhost:9092,localhost:9093";
    public static final String topic = "topic-demo";
    public static final String groupId = "group.demo";
    public static final AtomicBoolean isRunning = new AtomicBoolean(true);

    public static Properties initConfig() {
        Properties props = new Properties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer.client.id.demo");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return props;
    }

    public static void main(String[] args) {
        Properties props = initConfig();

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)){
//            consumer.subscribe(Collections.singletonList(topic));
            List<TopicPartition> partitions = new ArrayList<>();
            List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
            if (partitionInfos != null) {
                for (PartitionInfo tpInfo : partitionInfos) {
                    partitions.add(new TopicPartition(tpInfo.topic(), tpInfo.partition()));
                }
            }
            consumer.assign(partitions);

            Map<TopicPartition, Long> lastConsumedOffsetMap = new HashMap<>();

            while (isRunning.get()) {
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) {
                    break;
                }

                for (TopicPartition tp : records.partitions()) {
                    List<ConsumerRecord<String, String>> tpRecords = records.records(tp);
                    lastConsumedOffsetMap.put(tp, tpRecords.get(tpRecords.size() - 1).offset());
                    consumer.commitSync();//同步提交消费位移
                }
            }
            Map<TopicPartition, OffsetAndMetadata> offsetAndMetadataMap = consumer.committed(lastConsumedOffsetMap.keySet());
            offsetAndMetadataMap.forEach((tp, offsetAndMetadata) -> {
                System.out.println(tp);
                System.out.println("last consumed offset is " + lastConsumedOffsetMap.get(tp));
                System.out.println("committed offset is " + offsetAndMetadata.offset());
                long position = consumer.position(tp);
                System.out.println("the offset of the next record is " + position);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
