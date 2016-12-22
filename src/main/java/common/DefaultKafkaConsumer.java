package common;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Configuration
@PropertySource(value = {"classpath:kafka.properties"})
public class DefaultKafkaConsumer {
    Logger logger = Logger.getLogger(DefaultKafkaConsumer.class);
    @Value(value = "${brokerList}")
    private String brokerList;

    @Value("${sync}")
    private String sync;

    @Value("${topic}")
    private String topic;

    @Value("${zookeeper")
    private String zookeeper;

    private Consumer consumer;

    public DefaultKafkaConsumer() {

    }

    @PostConstruct
    public void initIt() {
        Properties kafkaConsumerProps = new Properties();
        kafkaConsumerProps.put("zookeeper.connect", zookeeper);
        //kafkaConsumerProps.put("group.id", groupId);
        kafkaConsumerProps.put("zookeeper.session.timeout.ms", "500");
        kafkaConsumerProps.put("zookeeper.sync.time.ms", "250");
        kafkaConsumerProps.put("auto.commit.interval.ms", "1000");
        consumer = new KafkaConsumer(kafkaConsumerProps);
    }
    //https://www.confluent.io/blog/tutorial-getting-started-with-the-new-apache-kafka-0-9-consumer-client/
    public void receive(String value) throws ExecutionException, InterruptedException {
        consumer.subscribe(Arrays.asList(topic));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                for (ConsumerRecord<String, String> record : records)
                    System.out.println(record.offset() + ": " + record.value());
            }
        } catch (WakeupException e) {
            // ignore for shutdown
        } finally {
            consumer.close();
        }
    }
}