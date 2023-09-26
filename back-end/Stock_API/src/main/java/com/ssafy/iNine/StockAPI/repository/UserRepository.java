package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUserId(String userId);
}
