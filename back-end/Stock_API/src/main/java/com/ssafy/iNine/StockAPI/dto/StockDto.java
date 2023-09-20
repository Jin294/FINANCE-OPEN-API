package com.ssafy.iNine.StockAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    private String stockCode; // 종목코드(주식코드)
    private String accountNumber; // 계좌번호
    private int amount; // 수량
    private double cost; // 최종 평균가
}
