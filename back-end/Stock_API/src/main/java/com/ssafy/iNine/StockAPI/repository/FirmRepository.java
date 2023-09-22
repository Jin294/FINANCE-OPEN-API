package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FirmRepository extends JpaRepository<Firm, String> {
    @Query("SELECT f.firmCode FROM Firm f")
    List<String> findAllFirmCode();
}
