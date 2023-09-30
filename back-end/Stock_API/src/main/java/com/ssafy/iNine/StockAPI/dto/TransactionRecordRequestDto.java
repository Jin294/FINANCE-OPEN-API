package com.ssafy.iNine.StockAPI.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionRecordRequestDto {
    String orgCode;
    String accountNum;
    String fromDate;
    String toDate;
    String nextPage;
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
