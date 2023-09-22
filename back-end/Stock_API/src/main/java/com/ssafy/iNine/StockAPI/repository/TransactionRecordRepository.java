package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.TransactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, String> {
    List<TransactionRecord> findByAccountNumberAndTransDtimeBetween(
            String accountNumber, LocalDateTime fromDate, LocalDateTime toDate);
}

