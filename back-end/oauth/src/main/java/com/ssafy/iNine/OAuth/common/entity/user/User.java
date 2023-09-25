package com.ssafy.iNine.OAuth.common.entity.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

/*
* OAuth 인증을하려는 이용자
* */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(length = 100)
    private String password;

    @Column(nullable = false, unique = true, length = 100)
    private String nickname;

    @Column(nullable = true, unique = false)
    private String state; // Y : 정상 회원 , L : 잠긴 계정, P : 패스워드 만료, A : 계정 만료

    @Column(name = "auth_send_time")
    private LocalDateTime authSendTime;

    @Column(name = "auth_request_time")
    private LocalDateTime authRequestTime;

    // 권한 (기본 권한 셋팅)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    // 계정 만료
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    // 잠긴 계정
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    // 패스워드 만료
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {
        if(isCredentialsNonExpired() && isAccountNonExpired() && isAccountNonLocked()){
            return true;
        }
        return false;
    }
}