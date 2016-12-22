package common;

import kafka.consumer.*;
import kafka.javaapi.consumer.ConsumerConnector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
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

    private ConsumerConnector consumer;

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
        consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(kafkaConsumerProps));
    }

    public void receive(String value) throws ExecutionException, InterruptedException {
        Map<String, Integer> message = new HashMap<>();
        message.put(topic, 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerStreams = consumer.createMessageStreams(message);
        List<KafkaStream<byte[], byte[]>> streams = consumerStreams.get(topic);
        for (KafkaStream stream: streams) {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
                System.out.println("Message: " + new String(it.next().message()));
            }
        }
    }
}