package com.pr.kafka.example.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * @author Oleksandr Prognimak
 * @created 12.01.2021 - 17:04
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
//@ContextConfiguration(classes = {AppConfiguration.class, IntegrationTestConfiguration.class,
//        TopicsConfiguration.class, ProducerConfiguration.class})

class KafkaConsumerServiceIT {

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void putTradeToIncomingTopic() throws Exception{


    }
}