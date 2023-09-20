package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByUserIdxAndFirmCode(int userIdx, String firmCode);
    Account findByAccountNumber(String accountNumber);
}
