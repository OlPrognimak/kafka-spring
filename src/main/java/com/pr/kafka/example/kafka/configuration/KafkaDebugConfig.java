package com.pr.kafka.example.kafka.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

@Configuration
//@EnableKafka
@RequiredArgsConstructor
public class KafkaDebugConfig {

    private final  KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @EventListener
    public void handleContextRefreshed(ContextRefreshedEvent event) {
        System.out.println("ContextRefreshedEvent: Number of listener containers = " +
                kafkaListenerEndpointRegistry.getListenerContainers().size());
        kafkaListenerEndpointRegistry.getListenerContainers().forEach(container ->
                System.out.println("Container ID: " + container.getListenerId())
        );
    }
}
