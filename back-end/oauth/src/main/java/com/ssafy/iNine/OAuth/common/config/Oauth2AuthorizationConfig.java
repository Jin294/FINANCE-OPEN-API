package com.ssafy.iNine.OAuth.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final DataSource dataSource;

    @Value("${RSA.privatekey}")
    private String privateKey;

    //OAuth2.0 인증 서버 보안 설정
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()") // 토큰유효성(/token/check_token) 접근을 위해 설정 모두 허용하지 않으면 해당 서버에서 토큰 접근이 불가능 하여 토큰을 DB에서 찾을 수 없다.
                .allowFormAuthenticationForClients(); //클라이언트 애플리케이션 폼 인증 허용
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //client 정보를 데이터베이스에서 가져오도록 설정 dataSource를 사용하여 클라이언트 정보 로드
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager) //클라이언트 인증  클라이언트 애플리케이션의 인증을 확인하고 클라이언트가 제출한 인증 정보가 유효한지 검사. 클라이언트의 신원이 확인되면 OAuth 2.0 인증 서버는 클라이언트에게 액세스 토큰을 발급.  grant_type password를 사용하기 위함
                .userDetailsService(userDetailsService) //인증 서버가 클라이언트 애플리케이션의 사용자 정보를 필요로 할 때 이 설정을 사용하여 사용자 정보를 얻는다. 인증 서버가 사용자의 권한을 확인하거나 사용자의 추가 정보를 가져올 때 userDetailsService를 사용. refrash token 발행시 유저 정보 검사
                .accessTokenConverter(jwtAccessTokenConverter()); // JWT 토큰 변환기 설정
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        // RSA 암호화 : 비 대칭키 암호화 : 공개키로 암호화 하면 개인키로 복호화
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwtkey.jks"), "i9ssafyi91234!!!".toCharArray());
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwtkey.jks"), privateKey.toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwtkey"));
        return converter;
    }
}
