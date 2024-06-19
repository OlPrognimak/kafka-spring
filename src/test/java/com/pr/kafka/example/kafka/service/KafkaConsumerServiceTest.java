package com.pr.kafka.example.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pr.kafka.example.model.TradeModel;
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

class KafkaConsumerServiceTest {

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void putTradeToIncomingTopic() throws Exception{
        TradeModel tradeModel = TradeModel.builder()
                .tradeId("123")
                .version(1)
                .bookId("bookId-1")
                .counterPartyId("ounterPartyId-1")
                .expired('N')
                .createdDate("25/07/2020")
                .maturityDate("26/07/2020")
                .build();
        String json  = objectMapper.writeValueAsString(tradeModel);

       // kafkaConsumerService.(tradeModel);

    }
}