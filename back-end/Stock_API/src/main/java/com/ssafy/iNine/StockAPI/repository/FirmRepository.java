package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FirmRepository extends JpaRepository<Firm, String> {
    @Query("SELECT f.firmCode FROM Firm f")
    List<String> findAllFirmCode();

    @Query("SELECT f.firmCode FROM Firm f where f.firmName like %:keyword%")
    Optional<String> findFirmCodeByContainingKeyword(String keyword);

    List<Firm> findAll();

    // 매개변수 firmCode는 :firmCode와 연결된다
    @Query("SELECT f.firmName FROM Firm f where f.firmCode = :firmCode")
    String getFirmName(String firmCode);
}
