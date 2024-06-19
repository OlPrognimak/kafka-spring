package com.pr.kafka.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 17:58
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.pr.kafka.example.persistency.repository"})
@EnableTransactionManagement
public class AppConfiguration {

}
