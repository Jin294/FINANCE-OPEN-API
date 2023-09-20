package com.ssafy.iNine.OAuth.domain.oauth.controller;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import com.ssafy.iNine.OAuth.domain.oauth.dto.OauthToken;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    // 클라이언트가 구현해야하는 코드 - 발급 받은 코드로 토큰 발행
    @RequestMapping("/callback")
    public OauthToken.response code(@RequestParam String code)  {

        String cridentials = "clientId:secretKey";
        // base 64로 인코딩 (basic auth 의 경우 base64로 인코딩 하여 보내야한다.)
        String encodingCredentials = new String(
                Base64.encodeBase64(cridentials.getBytes())
        );
        String requestCode = code;
        OauthToken.request.accessToken request = new OauthToken.request.accessToken(){{
            setCode(requestCode);
            setGrant_type("authorization_code");
            setRedirect_uri("http://localhost:8085/callback");
            setScope("read");
        }};

        //oauth 서버에 http 통신으로 토큰 발행
//        OauthToken.response oauthToken = (OauthToken.response) Unirest.post("http://localhost:8085/oauth/token")
//                .header("Authorization","Basic "+encodingCredentials)
//                .fields(request.getMapData())
//                .asObject(OauthToken.response.class).getBody();
//        System.out.println(oauthToken);

        HttpResponse<OauthToken.response> response = Unirest.post("http://localhost:8085/oauth/token")
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
//http://localhost:8085/oauth/authorize?response_type=code&client_id=79c85820-6ed5-4c99-a9d8-22c68374883b&redirect_uri=http://localhost:8085/callback2&scope=read
    @RequestMapping("/callback2")
    public OauthToken.response code2(@RequestParam String code)  {

        String cridentials = "79c85820-6ed5-4c99-a9d8-22c68374883b:eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjk1MDg5MzE4MzQ4LCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFAbmF2ZXIuY29tIiwicmVkaXJlY3RVcmkiOiJodHRwOi8vbG9jYWxob3N0OjgwODUvY2FsbGJhY2siLCJleHAiOjE3MjY2MjUzMTgsInN1YiI6ImFwaS10b2tlbiJ9.XPIZzR6pJKXtIxboYYiT9AlOpYRkTbRVgS6mRjg0FtQ";
        // base 64로 인코딩 (basic auth 의 경우 base64로 인코딩 하여 보내야한다.)
        String encodingCredentials = new String(
                Base64.encodeBase64(cridentials.getBytes())
        );
        String requestCode = code;
        OauthToken.request.accessToken request = new OauthToken.request.accessToken(){{
            setCode(requestCode);
            setGrant_type("authorization_code");
            setRedirect_uri("http://localhost:8085/callback2");
            setScope("read");
        }};

        //oauth 서버에 http 통신으로 토큰 발행
//        OauthToken.response oauthToken = (OauthToken.response) Unirest.post("http://localhost:8085/oauth/token")
//                .header("Authorization","Basic "+encodingCredentials)
//                .fields(request.getMapData())
//                .asObject(OauthToken.response.class).getBody();
//        System.out.println(oauthToken);

        HttpResponse<OauthToken.response> response = Unirest.post("http://localhost:8085/oauth/token")
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