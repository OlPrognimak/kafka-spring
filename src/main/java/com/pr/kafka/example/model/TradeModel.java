package com.pr.kafka.example.model;

import lombok.*;



@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 17:29
 */
public class TradeModel {
    private String tradeId;
    private int version;
    private String counterPartyId;
    private String bookId;
    private String maturityDate;
    private String createdDate;
    private char expired;
}
