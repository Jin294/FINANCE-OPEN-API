package com.ssafy.iNine.OAuth.domain.oauth.controller;

import com.ssafy.iNine.OAuth.domain.oauth.dto.OauthToken;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
@CrossOrigin("*")
public class DummyServerController {
    @Value("${url.host}")
    private String hostUrl;
    @GetMapping("/access-token")
    public OauthToken.response getAccessToken(@RequestParam String code) {
        String cridentials = "5caa02bf-f494-4c83-a7b5-7d7a1591ba2a:eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk2NDAzNzE5NjU4LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6IjQiLCJleHAiOjE3Mjc5Mzk3MTksInN1YiI6ImFwaS10b2tlbiJ9._Z9uZPp_QOFsE8KzIOSouMUlxNpFhSLxEuvkiT_iW_g";
        // base 64로 인코딩 (basic auth 의 경우 base64로 인코딩 하여 보내야한다.)
        String encodingCredentials = new String(
                Base64.encodeBase64(cridentials.getBytes())
        );
        String requestCode = code;
        OauthToken.request.accessToken request = new OauthToken.request.accessToken(){{
            setCode(requestCode);
            setGrant_type("authorization_code");
            setRedirect_uri(hostUrl+"/excardcontent");
            setScope("read");
        }};

        HttpResponse<OauthToken.response> response = Unirest.post(hostUrl+"/oauth/token")
                .header("Authorization", "Basic " + encodingCredentials)
                .fields(request.getMapData())
                .asObject(OauthToken.response.class);

        int statusCode = response.getStatus();
        String statusText = response.getStatusText();

        System.out.println("HTTP 요청 상태 코드: " + statusCode);
        System.out.println("HTTP 요청 상태 텍스트: " + statusText);
        OauthToken.response oauthToken = null;
        if (statusCode == 200) {
            oauthToken = response.getBody();
            // 여기에서 oauthToken을 처리할 수 있습니다.
        } else {
            // 상태 코드가 200이 아닌 경우 오류 처리를 수행할 수 있습니다.
            // 예: 오류 메시지 출력 또는 예외 처리
        }
        return oauthToken;
    }
}
