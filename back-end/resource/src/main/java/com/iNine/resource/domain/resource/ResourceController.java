package com.iNine.resource.domain.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api")
public class ResourceController {

    @RequestMapping("/main")
    public String main(Principal principal){
        log.info("user info:{}", principal.getName());
        return principal.getName();
    }
}