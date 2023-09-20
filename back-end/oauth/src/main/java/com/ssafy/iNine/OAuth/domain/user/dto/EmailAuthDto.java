package com.ssafy.iNine.OAuth.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString(of = {"email", "key"})
public class EmailAuthDto {

    @Id
    private String id;
    private String email;
    private String key;

    private LocalDateTime createdAt;

    public EmailAuthDto(String email, String key) {
        this.email = email;
        this.key = key;
        this.createdAt = LocalDateTime.now();
    }
}