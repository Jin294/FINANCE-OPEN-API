package com.ssafy.iNine.Document.domain.serviceprovider.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServiceProviderDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegistForm {
        private String email;
        private String password;
        private String user_class;
        private String area;

        public void encodePassword(String password) {
            this.password = password;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginForm {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class detail {
        private String email;
        private String password;
        private String ssafy_class;
        private String content;
        private LocalDateTime created_time;
        private LocalDateTime deleted_time;
        private Boolean is_deleted;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class info {
        private String email;
        private String password;
        private String ssafy_class;
        private String area;
        private LocalDateTime created_time;
        private LocalDateTime deleted_time;
        private Boolean is_deleted;
    }

}
