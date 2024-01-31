package com.ninlgde.kafka.admin;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaAdminClient {

    public static void describeTopic() {
        String[] options = new String[]{
                "--zookeeper", "localhost:32181/kafka",
                "--describe",
                "--topic", "other"
        };
        kafka.admin.TopicCommand.main(options);
    }

    public static void createTopic() {
        String brokerList =  "127.0.0.1:30092";
        String topic = "topic-admin";

        Properties props = new Properties();	// ①
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient client = AdminClient.create(props);		// ②

        NewTopic newTopic = new NewTopic(topic, 4, (short) 1);	//③
        CreateTopicsResult result = client.
                createTopics(Collections.singleton(newTopic));	//④
        try {
            result.all().get();			//⑤
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        client.close();
    }

    public static void deleteTopic() {
        String brokerList =  "127.0.0.1:30092";
        String topic = "gateway";

        Properties props = new Properties();	// ①
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient client = AdminClient.create(props);		// ②

//        NewTopic newTopic = new NewTopic(topic, 4, (short) 1);	//③
        DeleteTopicsResult result = client.
                deleteTopics(Collections.singleton(topic));	//④
        try {
            result.all().get();			//⑤
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        client.close();
    }

    public static void describeTopicConfig() throws ExecutionException,
            InterruptedException {
        String brokerList =  "172.16.0.249:9092";
        String topic = "topic-admin";

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        AdminClient client = AdminClient.create(props);

//        ConfigResource resource =
//                new ConfigResource(ConfigResource.Type.TOPIC, topic);//①
//        DescribeConfigsResult result =
//                client.describeConfigs(Collections.singleton(resource));//②
//        Config config = result.all().get().get(resource);//③
//        System.out.println(config);//④
        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topic);
        ConfigEntry entry = new ConfigEntry("cleanup.policy", "compact");
        Config config = new Config(Collections.singleton(entry));
        Map<ConfigResource, Config> configs = new HashMap<>();
        configs.put(resource, config);
        AlterConfigsResult result = client.alterConfigs(configs);
        result.all().get();
        client.close();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        describeTopic();
    }
}
