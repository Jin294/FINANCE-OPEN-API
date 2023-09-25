package com.ssafy.iNine.OAuth.domain.user.controller;

import com.ssafy.iNine.OAuth.common.response.CommonResponse;
import com.ssafy.iNine.OAuth.domain.user.service.UserDetailService;
import com.ssafy.iNine.OAuth.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class UserController {
    private final UserDetailService userDetailService;
    private final UserService userService;

    @RequestMapping("/confirm_access")
    public String confirm(HttpServletRequest request){
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) request.getSession().getAttribute("authorizationRequest");
        log.info("clientid:{}", authorizationRequest.getClientId());
        return "confirmForm";
    }

    /**
     * 이메일 인증 요청 API (이메일로 인증코드 전송)
     *
     * @param email 이메일
     * @return Only code and message
     */
    @GetMapping("/user/email/{email}")
    @ResponseBody
    public CommonResponse requestEmail(@PathVariable String email) {
        userService.sendEmail(email);
        return new CommonResponse(200, "이메일 인증 요청 성공");
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false)String error, Model model) {
        log.info("login form");
        if(error != null && error.equals("true")) {
            model.addAttribute("loginfail", true);
        }
//        return "login"; // login.html 또는 login.jsp와 같은 로그인 페이지 템플릿의 이름을 반환합니다.
        return "loginForm";
    }
}
