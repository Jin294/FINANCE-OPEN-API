package com.ssafy.iNine.Document.domain.serviceprovider.dto;

import com.ssafy.iNine.Document.common.entity.oauth.OAuthClientDetails;
import lombok.*;

@Getter
@Setter
public class OAuthClientDetailsDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OAuthClientRegistForm {
        private String web_server_redirect_uri;
        private String additional_information;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OAuthClientInfo {
        private String web_server_redirect_uri;
        private String additional_information;
        private String client_id;
        private String client_secret;
        private String scope;
        private String authorized_grant_types;

        public static OAuthClientInfo of(OAuthClientDetails oAuthClientDetails) {
            return OAuthClientInfo.builder()
                    .web_server_redirect_uri(oAuthClientDetails.getWebServerRedirectUri())
                    .additional_information(oAuthClientDetails.getAdditionalInformation())
                    .client_id(oAuthClientDetails.getClientId())
                    .client_secret(oAuthClientDetails.getClientSecret())
                    .scope(oAuthClientDetails.getScope())
                    .authorized_grant_types(oAuthClientDetails.getAuthorizedGrantTypes())
                    .build();
        }
    }
}
