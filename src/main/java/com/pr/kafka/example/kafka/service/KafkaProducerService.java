package com.pr.kafka.example.kafka.service;

import com.pr.kafka.example.model.TradeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.logging.Logger;

import static com.pr.kafka.example.kafka.configuration.TopicsConfiguration.ERROR_TOPIC;
import static com.pr.kafka.example.kafka.configuration.TopicsConfiguration.INCOMING_TOPIC;

/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 17:58
 */
@Service
@RequiredArgsConstructor
@EnableKafka
public class KafkaProducerService {
  private static final Logger logger = Logger.getLogger(KafkaProducerService.class.getName());

    private final KafkaTemplate<String, TradeModel> kafkaTemplate;
    private final KafkaAdmin kafkaAdmin;

    @PostConstruct
    public void init(){
        kafkaAdmin.initialize();
    }

    /**
     *
     * @param msg the message to be send
     */
    public void sendMessageToIncomingTopic(TradeModel msg) {
        logger.info("Send message to Incoming Topic: "+msg.toString());
        kafkaTemplate.send(INCOMING_TOPIC, msg);
    }

    public void sendMessageToErrorTopic(TradeModel msg) {
        logger.info("Send to Error Topic : "+msg.toString());
        kafkaTemplate.send(ERROR_TOPIC, msg);
    }
}
