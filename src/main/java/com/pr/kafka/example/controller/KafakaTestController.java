package com.pr.kafka.example.controller;

import com.pr.kafka.example.kafka.service.KafkaProducerService;
import com.pr.kafka.example.model.TradeModel;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.pr.kafka.example.kafka.service.KafkaConsumerService.INCOMING_LISTENER;

/**
 * @author Oleksandr Prognimak
 * @created 12.01.2021 - 16:24
 */
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class KafakaTestController {
    private static final Logger logger = LogManager.getLogger(KafakaTestController.class);

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @GetMapping(path="/start")
    public ResponseEntity<?> startIncomingConsumer(){
        MessageListenerContainer listenerContainer =
                kafkaListenerEndpointRegistry.getListenerContainer(INCOMING_LISTENER);

        if (listenerContainer == null) {
            logger.error("Listener container with ID '{}' is not found", INCOMING_LISTENER);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Listener container not found.");
        } else {
            listenerContainer.start();
            logger.info("######## The listener is started");
            return ResponseEntity.status(HttpStatus.OK).body("The listener is started successfully.");
        }

    }

    /**
     * 
     * @param tradeModel
     * @return
     */
    @PutMapping(path = "/trade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> putMessage(@RequestBody TradeModel tradeModel){
        try {
            kafkaProducerService.sendMessageToIncomingTopic(tradeModel);
        }catch(Exception ex){
            logger.error("Can not put message. ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
