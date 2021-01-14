package com.pr.kafka.example.persistency.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 17:37
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@SequenceGenerator(name ="trade_seq_gen",sequenceName="trade_seq", initialValue=1, allocationSize=100)
public class TradeEntity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="trade_seq_gen")
    private Long Id;
    private String tradeId;
    private int version;
    private String counterPartyId;
    private String bookId;
    private Date maturityDate;
    private Date createdDate;
    private char expired;
}
