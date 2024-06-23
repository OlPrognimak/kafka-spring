package com.pr.kafka.example.kafka.configuration;

import com.pr.kafka.example.kafka.serialization.TradeModelDeserializer;
import com.pr.kafka.example.model.TradeModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 17:57
 */
@Configuration
@EnableKafka
public class ConsumerConfiguration {
    @Value(value = "${kafka.bootstrap.address}")
    private String bootstrapAddress;
    @Value(value = "${kafka.consumer.group.id}")
    private String consumerGroupId;


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, TradeModel>>
    tradeListenerContainerFactory(ConsumerFactory<String, TradeModel> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, TradeModel> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setAutoStartup(false);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, TradeModel> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                consumerGroupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
                false);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                TradeModelDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props);
    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, TradeModel>
//    defaultListenerContainerFactory() {
//
//        ConcurrentKafkaListenerContainerFactory<String, TradeModel> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }


}
