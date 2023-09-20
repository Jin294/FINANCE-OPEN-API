package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FirmRepository extends JpaRepository<Firm, Integer> {
    List<String> findAllFirmCode();
}
