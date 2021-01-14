package com.pr.kafka.example.persistency.repository;

import com.pr.kafka.example.persistency.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oleksandr Prognimak
 * @created 11.01.2021 - 18:01
 */
@Repository
public interface TradeEntityRepository extends JpaRepository<TradeEntity, Long> {
}
