package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.TransactionRecord;
import com.ssafy.iNine.StockAPI.key.TransactionRecordKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, TransactionRecordKey> {
    List<TransactionRecord> findByAccountNumberAndIsFinal(String accountNumber, int isFinal);

    List<TransactionRecord> findByAccountNumberAndIsFinalAndIdTradedAtBetween(
            String accountNumber, int isFinal, LocalDateTime fromDate, LocalDateTime toDate);
}

