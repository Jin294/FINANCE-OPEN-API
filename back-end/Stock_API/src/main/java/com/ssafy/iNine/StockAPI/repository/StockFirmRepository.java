package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.StockFirm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockFirmRepository extends JpaRepository<StockFirm, Integer> {
    List<String> findAllFirmCode();
}
