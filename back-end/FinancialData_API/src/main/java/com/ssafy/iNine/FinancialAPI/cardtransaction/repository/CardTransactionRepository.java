package com.ssafy.iNine.FinancialAPI.cardtransaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ssafy.iNine.FinancialAPI.entity.CardTransaction;

import java.util.List;

public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {

    List<CardTransaction> findByCard_CardId(Long cardId);


}
