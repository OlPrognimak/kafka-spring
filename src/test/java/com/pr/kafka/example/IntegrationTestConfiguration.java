package com.pr.kafka.example;

import com.pr.kafka.example.kafka.service.KafkaConsumerService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Oleksandr Prognimak
 * @created 12.01.2021 - 17:06
 */
@TestConfiguration
public class IntegrationTestConfiguration {

    @Bean
    public KafkaConsumerService kafkaConsumerService(){
        return new KafkaConsumerService();
    }
}
