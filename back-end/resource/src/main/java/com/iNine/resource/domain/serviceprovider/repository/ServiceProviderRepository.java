package com.iNine.resource.domain.serviceprovider.repository;

import com.iNine.resource.common.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
    @Query("SELECT s FROM ServiceProvider s WHERE s.apiToken=:apiToken AND s.isDeleted=false")
    Optional<ServiceProvider> selectByApiToken(@Param("apiToken") String apiToken);
}
