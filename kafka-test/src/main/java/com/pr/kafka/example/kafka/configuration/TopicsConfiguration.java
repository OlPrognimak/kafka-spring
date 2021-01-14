package com.pr.kafka.example.kafka.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 18:53
 */
@Configuration
public class TopicsConfiguration {

    public static final String INCOMING_TOPIC = "incoming_TOPIC";
    public static final String ERROR_TOPIC = "error_TOPIC";

    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;
    @Value(value = "${app.kafka.replication.factor}")
    private Integer replicationFactor;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }


    @Bean("incomingTopic")
    @Primary
    public NewTopic incomingTopic() {
        return new NewTopic(INCOMING_TOPIC, 1, replicationFactor.shortValue());
    }

    @Bean("errorTopic")
    public NewTopic errorTopic() {
        return new NewTopic(ERROR_TOPIC, 1, replicationFactor.shortValue());
    }
}
