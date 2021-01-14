package com.pr.kafka.example.kafka.serialization;

import com.pr.kafka.example.model.TradeModel;

/**
 * @author Oleksandr Prognimak
 * @created 13.01.2021 - 11:16
 */
public class TradeModelDeserializer extends JsonDeserializer<TradeModel> {
    public TradeModelDeserializer (){
        super.deserializedClass = TradeModel.class;
    }
}
