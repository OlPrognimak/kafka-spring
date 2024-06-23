package com.pr.kafka.example.persistency.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeEntity that = (TradeEntity) o;
        return version == that.version && expired == that.expired && Objects.equals(Id, that.Id) && Objects.equals(tradeId, that.tradeId) && Objects.equals(counterPartyId, that.counterPartyId) && Objects.equals(bookId, that.bookId) && Objects.equals(maturityDate, that.maturityDate) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, tradeId, version, counterPartyId, bookId, maturityDate, createdDate, expired);
    }
}
