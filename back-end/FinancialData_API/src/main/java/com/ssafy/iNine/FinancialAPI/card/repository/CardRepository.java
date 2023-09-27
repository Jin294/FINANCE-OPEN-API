package com.ssafy.iNine.FinancialAPI.card.repository;

import com.ssafy.iNine.FinancialAPI.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {



    List<Card> findByUserId(String userId);
}
