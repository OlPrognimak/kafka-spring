package com.pr.kafka.example.persistency.repository;

import com.pr.kafka.example.IntegrationTestConfiguration;
import com.pr.kafka.example.persistency.entity.TradeEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import( {IntegrationTestConfiguration.class})
public class TradeEntityRepositoryTest {

    @Autowired
    private TradeEntityRepository tradeEntityRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void save() {

        TradeEntity tradeEntity = TradeEntity.builder()
                .expired('N')
                        .tradeId("tradeId")
                                .bookId("bookId")
                                        .createdDate(new Date())
                                                .maturityDate(new Date())
                                                        .counterPartyId("counterPartyId")
                                                                .build();

        tradeEntityRepository.save(tradeEntity);
        Optional<TradeEntity> result = tradeEntityRepository.findById(tradeEntity.getId());
        assertTrue(result.isPresent());
        assertEquals(tradeEntity.getId(), result.get().getId());
        assertEquals(tradeEntity.getExpired(), result.get().getExpired());
        assertEquals(tradeEntity.getTradeId(), result.get().getTradeId());
        assertEquals(tradeEntity.getBookId(), result.get().getBookId());

    }
}