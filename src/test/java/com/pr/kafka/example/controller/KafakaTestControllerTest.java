package com.pr.kafka.example.controller;

import com.pr.kafka.example.IntegrationTestConfiguration;
import com.pr.kafka.example.model.TradeModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Import( {IntegrationTestConfiguration.class})
class KafakaTestControllerTest {

    @Autowired
    RestTemplate restClient;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void startConsumer() {
        RequestEntity request = RequestEntity
                .get("http://localhost:" + (9080) + "/start")
                .header("Content-Type", "application/json")
                .accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<String> exchange = restClient.exchange(request, String.class);
        assertEquals(200, exchange.getStatusCodeValue());
    }


    @Test
    public void testController() throws URISyntaxException {

        TradeModel trade = TradeModel.builder()
                .tradeId("T123")
                .version(1)
                .counterPartyId("C456")
                .bookId("B789")
                .maturityDate("2024-12-31")
                .createdDate("2024-06-22")
                .expired('N')
                .build();

        RequestEntity<TradeModel> request = RequestEntity
                .put(new URI("http://localhost:"+(9080)+"/trade"))
                .header("Content-Type", "application/json")
                .accept( MediaType.APPLICATION_JSON)
                .body(trade);

        ResponseEntity<Object> exchange = restClient.exchange(request, Object.class);
        assertEquals(200,exchange.getStatusCodeValue());

    }

}