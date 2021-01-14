package com.pr.kafka.example.kafka.service;

import com.pr.kafka.example.kafka.configuration.TopicsConfiguration;
import com.pr.kafka.example.model.TradeModel;
import com.pr.kafka.example.persistency.repository.TradeEntityRepository;
import com.pr.kafka.example.utils.MapUtility;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 17:59
 */
@Service
public class KafkaConsumerService {
    private static final Logger logger = Logger.getLogger(KafkaConsumerService.class.toString());
    public static final String INCOMING_LISTENER = "incomingListener";
    @Autowired
    private TradeEntityRepository tradeEntityRepository;
    @Autowired
    private KafkaAdmin kafkaAdmin;
    @Autowired()
    private NewTopic incomingTopic;
    @Qualifier("errorTopic")
    @Autowired
    private NewTopic errorTopic;
    //@Qualifier("org.springframework.kafka.config.internalKafkaListenerEndpointRegistry")
    @Autowired()
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    @Autowired()
    private KafkaProducerService kafkaProducerService;
    @Value(value = "${kafka.consumer.group.id}")
    private String consumerGroupId;

    @PostConstruct
    public void init(){
        kafkaAdmin.initialize();
    }

    /**
     *
     * @param tradeModel
     */
    @Transactional
    @KafkaListener(
            id = INCOMING_LISTENER,
            topics = TopicsConfiguration.INCOMING_TOPIC,
            containerFactory = "tradeListenerContainerFactory"
        )
    public void consumeFromIncomingTopic(TradeModel tradeModel){

           try {
               logger.info("Consume message :"+tradeModel.toString());
               tradeEntityRepository.save(MapUtility.mapTradeModelToEntity(tradeModel));

           }catch(Exception ex){
               kafkaProducerService.sendMessageToErrorTopic(tradeModel);
               stopConsuming();
               ex.printStackTrace();
           }

    }

    private void stopConsuming(){
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
