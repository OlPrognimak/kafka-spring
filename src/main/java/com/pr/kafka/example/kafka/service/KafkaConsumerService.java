package com.pr.kafka.example.kafka.service;

import com.pr.kafka.example.kafka.configuration.TopicsConfiguration;
import com.pr.kafka.example.model.TradeModel;
import com.pr.kafka.example.persistency.repository.TradeEntityRepository;
import com.pr.kafka.example.utils.MapUtility;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 17:59
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    public static final String INCOMING_LISTENER = "incomingListener";
    private static final Logger logger = LogManager.getLogger(KafkaConsumerService.class.toString());

    private final TradeEntityRepository tradeEntityRepository;

    private final KafkaAdmin kafkaAdmin;
    @Autowired
    @Qualifier("incomingTopic")
    private  NewTopic incomingTopic;
    @Autowired
    @Qualifier("errorTopic")
    private  NewTopic errorTopic;
   // @Qualifier("org.springframework.kafka.config.internalKafkaListenerEndpointRegistry")
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final KafkaProducerService kafkaProducerService;

    @Value(value = "${kafka.consumer.group.id}")
    private String consumerGroupId;

    @PostConstruct
    public void init() {
        kafkaAdmin.initialize();
    }

    /**
     * @param tradeModel
     */
    @KafkaListener(
            id = INCOMING_LISTENER,
            topics = TopicsConfiguration.INCOMING_TOPIC,
            containerFactory = "tradeListenerContainerFactory"
    )
    @Transactional
    public void consumeFromIncomingTopic(TradeModel tradeModel,
                                         @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                         @Header(KafkaHeaders.OFFSET) int offset,
                                         Acknowledgment ack) {
        try {
            logger.info("Offset: " + offset + " :Consume message :" + tradeModel.toString());

            if(tradeModel.getExpired() != 'N') {
                throw new RuntimeException("Trade Expired");
            }
            tradeEntityRepository.save(MapUtility.mapTradeModelToEntity(tradeModel));
            ack.acknowledge();
        } catch (Throwable ex) {
            kafkaProducerService.sendMessageToErrorTopic(tradeModel);
            stopConsuming();
            logger.error(ex);
        }
    }

    private void stopConsuming() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                MessageListenerContainer listenerContainer =
                        kafkaListenerEndpointRegistry.getListenerContainer(INCOMING_LISTENER);
                listenerContainer.stop();
                logger.info("######## The listener (Incoming consumer) is stopped");
            }
        });
    }
}
