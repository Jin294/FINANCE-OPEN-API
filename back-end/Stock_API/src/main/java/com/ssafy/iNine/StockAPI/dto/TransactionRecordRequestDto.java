package com.ssafy.iNine.StockAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionRecordRequestDto {
    @JsonProperty("org_code")
    String orgCode;

    @JsonProperty("account_num")
    String accountNum;

    @JsonProperty("from_date")
    String fromDate;

    @JsonProperty("to_date")
    String toDate;

    @JsonProperty("next_page")
    String nextPage;

    @JsonProperty("limit")
    String limit;

    @Override
    public String toString() {
        return "TransactionRecordRequestDto{" +
                "orgCode='" + orgCode + '\'' +
                ", accountNum='" + accountNum + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", nextPage='" + nextPage + '\'' +
                ", limit='" + limit + '\'' +
                '}';
    }
}
