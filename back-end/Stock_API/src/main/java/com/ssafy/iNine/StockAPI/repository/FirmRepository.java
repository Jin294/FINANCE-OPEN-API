package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.Firm;
import com.ssafy.iNine.StockAPI.dto.FirmDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FirmRepository extends JpaRepository<Firm, String> {
    @Query("SELECT f.firmCode FROM Firm f")
    List<String> findAllFirmCode();

    @Query(nativeQuery = true, value = "SELECT * FROM firm WHERE idx = FLOOR(1 + RAND() * 39)")
    Firm getSingleRandomFirmCode();
}
