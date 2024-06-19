package com.pr.kafka.example.utils;

import com.pr.kafka.example.model.TradeModel;
import com.pr.kafka.example.persistency.entity.TradeEntity;

/**
 * @author Oleksandr Prognimak
 * @created 12.01.2021 - 13:40
 */
public final class MapUtility {

    private MapUtility(){

    }

    public static final TradeEntity mapTradeModelToEntity(TradeModel tradeModel){
        return TradeEntity.builder()
                .bookId(tradeModel.getBookId())
                .tradeId(tradeModel.getTradeId())
                .counterPartyId(tradeModel.getCounterPartyId())
                .version(tradeModel.getVersion())
                .expired(tradeModel.getExpired())
                .createdDate(DateUtility.stringToDate(tradeModel.getCreatedDate()))
                .maturityDate(DateUtility.stringToDate(tradeModel.getMaturityDate()))
                .build();

    }
}
