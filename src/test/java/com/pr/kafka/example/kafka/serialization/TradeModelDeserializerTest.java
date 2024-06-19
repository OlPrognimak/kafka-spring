package com.pr.kafka.example.kafka.serialization;

import com.pr.kafka.example.model.TradeModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleksandr Prognimak
 * @created 13.01.2021 - 11:47
 */
class TradeModelDeserializerTest {
    String json = "{\"tradeId\":\"123\",\"version\":1,\"counterPartyId\":\"ounterPartyId-1\",\"bookId\":\"bookId-1\",\"maturityDate\":\"26/07/2020\",\"createdDate\":\"25/07/2020\",\"expired\":\"N\"}";

    @Test
    public void testDeserializer(){
        TradeModelDeserializer deserializer = new TradeModelDeserializer();
        TradeModel tradeModel = deserializer.deserialize("", json.getBytes());
        assertNotNull(tradeModel);
    }

}