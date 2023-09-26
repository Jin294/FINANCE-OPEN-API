package com.ssafy.iNine.FinancialAPI.cardtransaction.repository;

import com.ssafy.iNine.FinancialAPI.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
