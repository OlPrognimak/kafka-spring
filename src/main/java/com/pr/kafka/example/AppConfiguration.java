package com.pr.kafka.example;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 17:58
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.pr.kafka.example.persistency.repository"})
@EntityScan(basePackages = {"com.pr.kafka.example.persistency.entity"})
@EnableTransactionManagement
@EnableKafka
public class AppConfiguration {

}
