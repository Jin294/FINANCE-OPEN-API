package com.iNine.resource.common.config;

import com.google.gson.Gson;
import com.iNine.resource.common.entity.JWTKey;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.StringUtils;

@EnableResourceServer
@Configuration
public class Oauth2ResourceConfig extends ResourceServerConfigurerAdapter {
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String publicKeyUri; // key 정보를 받아오기 위한 인증서버 URL 선언
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/main", "/api/cards/**", "/api/stock/**")
                .access("#oauth2.hasAnyScope('read')")
                .anyRequest()
                .permitAll();
//                .authenticated();
    }

    // 토큰 저장소를 jwt 체크하는것으로 수정
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        try{
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setVerifierKey(getPublicKeyValue(publicKeyUri));
            return converter;
        } catch (Exception e){
            return new JwtAccessTokenConverter();
        }
    }

    private String getPublicKeyValue(String uriKey) {
        JsonNode response = Unirest.get(uriKey)
                .asJson().getBody();
        return StringUtils.isEmpty(response.toString()) ? "" : new Gson().fromJson(response.toString(), JWTKey.class).getValue();
    }
}
