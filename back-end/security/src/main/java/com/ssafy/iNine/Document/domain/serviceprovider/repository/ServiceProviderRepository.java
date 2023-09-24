package com.ssafy.iNine.Document.domain.serviceprovider.repository;

import com.ssafy.iNine.Document.common.entity.oauth.OAuthClientDetails;
import com.ssafy.iNine.Document.common.entity.user.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

    Optional<ServiceProvider> findByEmailAndPassword(String email, String password);
    Optional<ServiceProvider> findByServiceProviderId(String userId);

    @Query("SELECT COUNT(s) > 0 FROM ServiceProvider s WHERE s.email=:email")
    boolean isDuplicatedEmail(@Param("email") String email);

    @Query("SELECT s FROM ServiceProvider s WHERE s.email=:email AND s.isDeleted=false")
    Optional<ServiceProvider> findByEmail(@Param("email") String email);

    @Modifying
    @Query("UPDATE ServiceProvider s SET s.isDeleted = true, s.deletedTime = :deletedTime WHERE s.serviceProviderId = :serviceProviderId")
    void deleteUser(@Param("serviceProviderId") Long serviceProviderId, @Param("deletedTime")LocalDateTime deletedTime);

    @Modifying
    @Query("UPDATE ServiceProvider s SET s.password = :password WHERE s.serviceProviderId = :serviceProviderId")
    void modifyPassword(@Param("serviceProviderId") Long serviceProviderId, @Param("password") String password);

    @Modifying
    @Query("UPDATE ServiceProvider s SET s.apiToken = :apiToken WHERE s.serviceProviderId = :serviceProviderId")
    void modifyApiToken(@Param("serviceProviderId") Long serviceProviderId, @Param("apiToken") String apiToken);
}
