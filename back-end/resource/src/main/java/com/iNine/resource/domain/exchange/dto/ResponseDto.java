package com.iNine.resource.domain.exchange.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {
    private int code;
    private String message;
    private T data;
}
