package com.iNine.resource.domain.mydata;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mydata")
public class MyDataController {
    @GetMapping
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
