package com.ssafy.iNine.Document.common.entity.oauth;

import com.ssafy.iNine.Document.common.entity.user.ServiceProvider;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "oauth_client_details")
public class OAuthClientDetails {
    @Id
    @Column(name = "client_id", length=300)
    private String clientId;

    @Column(name = "resource_ids")
    private String resourceIds;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "scope")
    private String scope;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    @Column(name = "web_server_redirect_uri", length=4096)
    private String webServerRedirectUri;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name = "additional_information", length = 4096)
    private String additionalInformation;

    @Column(name = "autoapprove")
    private String autoapprove;

    @JoinColumn(name = "service_provider_id")
    @OneToOne(fetch = FetchType.LAZY)
    private ServiceProvider serviceProvider;
}
