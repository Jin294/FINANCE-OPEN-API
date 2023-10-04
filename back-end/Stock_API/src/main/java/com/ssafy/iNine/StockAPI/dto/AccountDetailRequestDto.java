package com.ssafy.iNine.StockAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AccountDetailRequestDto {
    @JsonProperty("org_code")
    private String orgCode;

    @JsonProperty("account_num")
    private String accountNum;

    @JsonProperty("search_timestamp")
    private LocalDateTime searchTimestamp;

    @JsonProperty("from_date")
    private LocalDateTime fromDate;

    @JsonProperty("to_date")
    private LocalDateTime toDate;

    @JsonProperty("next_page")
    private String nextPage;

    @JsonProperty("limit")
    private int limit;
}
