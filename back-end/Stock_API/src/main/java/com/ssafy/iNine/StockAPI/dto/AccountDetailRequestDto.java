package com.ssafy.iNine.StockAPI.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AccountDetailRequestDto {
    private String orgCode;
    private String accountNum;
    private LocalDateTime searchTimestamp;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String nextPage;
    private int limit;
}
