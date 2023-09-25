package com.ssafy.iNine.Document.domain.serviceprovider.repository;

import com.ssafy.iNine.Document.common.entity.oauth.OAuthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, String> {

    @Query("SELECT o FROM OAuthClientDetails o JOIN FETCH o.serviceProvider WHERE o.serviceProvider.serviceProviderId = :userId")
    List<OAuthClientDetails> getOAuthClientDetailsByClient(@Param("userId") Long userId);
}
