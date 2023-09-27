package com.iNine.resource.common.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Data
public class JWTKey {

    String alg = "";
    String value = "";

    public String getValue() {
        if (!StringUtils.isEmpty(value)){
            return value;
        }
        return "";
    }
}