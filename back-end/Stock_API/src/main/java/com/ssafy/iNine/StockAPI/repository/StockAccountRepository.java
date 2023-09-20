package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.StockAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockAccountRepository extends JpaRepository<StockAccount, Integer> {
    List<StockAccount> findByUserIdxAndFirmCode(int userIdx, String firmCode);
    StockAccount findByAccountNumber(String accountNumber);
}
