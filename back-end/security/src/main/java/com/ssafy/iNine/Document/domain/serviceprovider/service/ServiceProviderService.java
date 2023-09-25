package com.ssafy.iNine.Document.domain.serviceprovider.service;

import com.ssafy.iNine.Document.common.entity.oauth.OAuthClientDetails;
import com.ssafy.iNine.Document.common.entity.user.ServiceProvider;
import com.ssafy.iNine.Document.common.exception.CommonException;
import com.ssafy.iNine.Document.common.exception.ExceptionType;
import com.ssafy.iNine.Document.domain.serviceprovider.dto.OAuthClientDetailsDto;
import com.ssafy.iNine.Document.domain.serviceprovider.dto.ServiceProviderDto;
import com.ssafy.iNine.Document.domain.serviceprovider.repository.OAuthClientDetailsRepository;
import com.ssafy.iNine.Document.domain.serviceprovider.repository.ServiceProviderRepository;
import com.ssafy.iNine.Document.common.security.JwtAuthenticationProvider;
import com.ssafy.iNine.Document.common.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class ServiceProviderService {

    private final PasswordEncoder passwordEncoder;
    private final ServiceProviderRepository userRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final OAuthClientDetailsRepository oAuthClientDetailsRepository;
    private final JwtUtil jwtUtil;

    public Map<String, String> login(ServiceProviderDto.LoginForm loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = jwtAuthenticationProvider.authenticate(authenticationToken);
        ServiceProvider principal = (ServiceProvider) authentication.getPrincipal();

        String accessToken = JwtUtil.generateAccessToken(String.valueOf(principal.getServiceProviderId()));
        String refreshToken = JwtUtil.generateRefreshToken(String.valueOf(principal.getServiceProviderId()));

        Map<String, String> result = new HashMap<>();
        result.put("access-token", accessToken);
        result.put("refresh-token", refreshToken);

        return result;
    }

    public void signUp(ServiceProviderDto.RegistForm registForm) {
        registForm.encodePassword(passwordEncoder.encode(registForm.getPassword()));  // 비밀번호 암호화

        // 이메일, 닉네임 중복체크 처리
        if (userRepository.findByEmail(registForm.getEmail()).isPresent()) {
            log.info("duplicate error");
            throw new CommonException(ExceptionType.DUPLICATED_EMAIL);
        }
        ServiceProvider serviceProvider = ServiceProvider.builder().
                email(registForm.getEmail()).
                password(registForm.getPassword()).
                isDeleted(false).
                createdTime(LocalDateTime.now()).
                apiToken(jwtUtil.generateApiToken(registForm.getEmail())).
                build();

        if(registForm.getUser_class() != null) serviceProvider.setUserClass(registForm.getUser_class());
        if(registForm.getArea() != null) serviceProvider.setArea(registForm.getArea());
        log.info("user id:{}", serviceProvider.getServiceProviderId());
        userRepository.save(serviceProvider);
    }

    public void setApiToken(Long userId) {
        ServiceProvider serviceProvider = userRepository.findById(userId).orElseThrow(()->{
            return new CommonException(ExceptionType.USER_NOT_FOUND);
        });
        userRepository.modifyApiToken(userId, JwtUtil.generateApiToken(serviceProvider.getEmail()));
    }
    public String getApiToken(Long userId) {
        ServiceProvider serviceProvider = userRepository.findById(userId).orElseThrow(()->{
            return new CommonException(ExceptionType.USER_NOT_FOUND);
        });
        return serviceProvider.getApiToken();
    }


    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId, LocalDateTime.now());
    }

    public void modifyPassword(Long userId, String password) {
        userRepository.modifyPassword(userId, passwordEncoder.encode(password));
    }

    public List<OAuthClientDetailsDto.OAuthClientInfo> getOAuthClient(Long userId) {
        List<OAuthClientDetails> oAuthClientDetailsList = oAuthClientDetailsRepository.getOAuthClientDetailsByClient(userId);
        List<OAuthClientDetailsDto.OAuthClientInfo> clientInfoList = new ArrayList<>();
        oAuthClientDetailsList.forEach((oAuthClientDetails -> {
            clientInfoList.add(OAuthClientDetailsDto.OAuthClientInfo.of(oAuthClientDetails));
        }));

        return clientInfoList;
    }

    public void setOAuthClient(Long userId, OAuthClientDetailsDto.OAuthClientRegistForm oAuthClientRegistForm) {
        ServiceProvider serviceProvider = userRepository.findById(userId)
                .orElseThrow(() -> {
                    return new CommonException(ExceptionType.USER_NOT_FOUND);
                });
        OAuthClientDetails oAuthClientDetails = OAuthClientDetails.builder()
                .clientId(String.valueOf(UUID.randomUUID()))
                .accessTokenValidity(36000)
//                .authorizedGrantTypes("authorization_code,implicit,password,client_credentials,refresh_token")
                .authorizedGrantTypes("authorization_code")
                .scope("read, write")
                .autoapprove("false")
                .clientSecret(JwtUtil.generateSecretKey(serviceProvider.getEmail(), oAuthClientRegistForm.getWeb_server_redirect_uri()))
                .refreshTokenValidity(360000)
                .webServerRedirectUri(oAuthClientRegistForm.getWeb_server_redirect_uri())
                .serviceProvider(serviceProvider)
                .build();

        if(oAuthClientRegistForm.getAdditional_information() != null) oAuthClientDetails.setAdditionalInformation(oAuthClientRegistForm.getAdditional_information());
        oAuthClientDetailsRepository.save(oAuthClientDetails);
    }
}
