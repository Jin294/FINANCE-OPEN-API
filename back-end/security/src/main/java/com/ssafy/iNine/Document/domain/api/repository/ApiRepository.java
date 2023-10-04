package com.ssafy.iNine.Document.domain.api.repository;

import com.ssafy.iNine.Document.common.entity.api.Api;
import com.ssafy.iNine.Document.common.entity.user.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApiRepository extends JpaRepository<Api, Long> {
    List<Api> findAll();

    @Query("SELECT a FROM Api a JOIN FETCH a.apiData JOIN FETCH a.category WHERE a.apiDocsId = :apiId")
    Optional<Api> getApiById(@Param("apiId") Long apiId);
}
