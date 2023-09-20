package com.ssafy.iNine.StockAPI.dto;

import com.ssafy.iNine.StockAPI.key.TransactionRecordKey;

import javax.persistence.EmbeddedId;
import java.time.LocalDateTime;

public class TransactionRecordDto {
    private String recordId;
    private LocalDateTime tradedAt;
    private int userIdx;
    private String firmCode; // 증권사코드
    private String accountNumber; // 계좌번호
    private String stockCode; // 주식코드
    private int status; // 0 : 매수, 1 : 매도
    private int isFinal; // 0 : 체결안됨, 1 : 체결됨
    private int amount; // 수량
    private double cost; // 평단가
}
