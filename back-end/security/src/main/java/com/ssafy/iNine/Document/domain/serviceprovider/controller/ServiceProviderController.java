package com.ssafy.iNine.Document.domain.serviceprovider.controller;

import com.ssafy.iNine.Document.common.entity.oauth.OAuthClientDetails;
import com.ssafy.iNine.Document.domain.serviceprovider.dto.OAuthClientDetailsDto;
import com.ssafy.iNine.Document.domain.serviceprovider.dto.ServiceProviderDto;
import com.ssafy.iNine.Document.domain.serviceprovider.service.ServiceProviderService;
import com.ssafy.iNine.Document.common.response.CommonResponse;
import com.ssafy.iNine.Document.common.response.DataResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/docs/service")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ServiceProviderController {

    private final ServiceProviderService serviceProviderService;
    @PostMapping
    public CommonResponse signUp(@RequestBody ServiceProviderDto.RegistForm registForm) {
        serviceProviderService.signUp(registForm);
        return new CommonResponse(200, "success");
    }

    @PostMapping("/login")
    public DataResponse<?> login(@RequestBody ServiceProviderDto.LoginForm loginDto) {
        Map<String, String> token = serviceProviderService.login(loginDto);
        return new DataResponse<>(200, "success", token);
    }

    @DeleteMapping
    public CommonResponse deleteUser(Principal principal) {
        Long serviceProviderId = Long.parseLong(principal.getName());
        serviceProviderService.deleteUser(serviceProviderId);
        return new CommonResponse(200, "success");
    }

    @PutMapping("/password")
    public CommonResponse modifyPassword(Principal principal, @RequestBody Map<String, String> passwordMap) {
        Long serviceProviderId = Long.parseLong(principal.getName());
        serviceProviderService.modifyPassword(serviceProviderId, passwordMap.get("password"));
        return new CommonResponse(200, "success");
    }

    @PostMapping("/client")
    public DataResponse<?> setClient(Principal principal, @RequestBody OAuthClientDetailsDto.OAuthClientRegistForm oAuthClientRegistForm) {
        Long serviceProviderId = Long.parseLong(principal.getName());
        OAuthClientDetails data = serviceProviderService.setOAuthClient(serviceProviderId, oAuthClientRegistForm);
        return new DataResponse(200, "success", data);
    }

    @GetMapping("/client")
    public DataResponse<?> getClient(Principal principal) {
        Long serviceProviderId = Long.parseLong(principal.getName());
        List<OAuthClientDetailsDto.OAuthClientInfo> data = serviceProviderService.getOAuthClient(serviceProviderId);
        return new DataResponse<>(200, "success", data);
    }


    @GetMapping("/token")
    public DataResponse<String> getApiToken(Principal principal) {
        Long serviceProviderId = Long.parseLong(principal.getName());
        String apiToken = serviceProviderService.getApiToken(serviceProviderId);
        return new DataResponse<>(200, "success", apiToken);
    }

    @PostMapping("/token")
    public DataResponse<?> setApiToken(Principal principal) {
        Long serviceProviderId = Long.parseLong(principal.getName());
        String token = serviceProviderService.setApiToken(serviceProviderId);
        log.info(token);
        return new DataResponse<>(201, "changed", token);
    }
}
