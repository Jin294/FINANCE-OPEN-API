package com.iNine.resource.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonException extends RuntimeException {
     private final ExceptionType exceptionType;
}
