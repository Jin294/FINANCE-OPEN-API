package com.ssafy.iNine.Document.common.entity.user;

import com.ssafy.iNine.Document.common.entity.oauth.OAuthClientDetails;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="service_provider")
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_provider_id")
    private Long serviceProviderId;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "class")
    private String userClass;

    @Column(name = "area", length = 30)
    private String area;

    @Column(name = "api_token", length = 300)
    private String apiToken;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "deleted_time")
    private LocalDateTime deletedTime;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;
}
