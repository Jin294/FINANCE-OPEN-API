package com.ssafy.iNine.FinancialAPI.cardtransaction.repository;

import com.ssafy.iNine.FinancialAPI.entity.CardTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CardTransactionRepository extends MongoRepository<CardTransaction, String> {



    CardTransaction findFirstByDtimeAfterOrderByDtimeAsc(Timestamp dtime);



    List<CardTransaction> findAllByCardIdAndDtimeBetweenOrderByDtimeAsc(Long cardId, Timestamp fromDate, Timestamp toDate);

}
