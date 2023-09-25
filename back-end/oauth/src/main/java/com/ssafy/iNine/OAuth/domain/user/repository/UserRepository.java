package com.ssafy.iNine.OAuth.domain.user.repository;

import com.ssafy.iNine.OAuth.common.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById (Long );

    @Modifying
    @Query("update User u set u.password =:password where u.id =:userId")
    int setUserPassword(@Param("password") String password, @Param("userId") Long userId);

    @Modifying
    @Query("update User u set u.authSendTime =:authSendTime where u.id =:userId")
    int setAuthSendTime(@Param("authSendTime") LocalDateTime authSendTime, @Param("userId") Long userId);
}
