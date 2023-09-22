package com.ssafy.iNine.StockAPI.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class TransactionRecord {
    private String userId;
    private String orgCode; // 증권사코드
    private String accountNumber; // 계좌번호

    private String prodName; // 종목명 (상품명, 거래가 발생한 상품의 명칭)
    private String prodCode; // 종목코드 (상품코드)
    private LocalDateTime transDtime; // 거래일시 또는 거래일자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transNo; // 거래번호 (해당 일자의 거래 특정 번호, 없을 경우 미회신)
    private String transType; // 거래종류 (거래종류 특정코드)
    private String transTypeDetail; // 상세 거래 종류 (코드가 아닌 상세 거래 종류 명)
    private double transNum; // 거래수량 (해당종목이 본 거래에서 거래된 총 수량)
    private double baseAmt; // 거래단가 (해당종목이 본 거래에서 거래된 단위 가격)
    private double transAmt; // 거래금액 (해당종목이 본 거래에서 거래된 총 금액)
    private double settleAmt; // 정산금액 (거래대금에서 제세공과금 등을 차감한 금액)
    private double balanceAmt; // 거래후잔액 (해당 계좌의 현금 잔액(예수금))
    private String currencyCode = "KRW"; // 거래에 적용된 통화코드 (거래에 적용된 통화코드 (ISO 4217 준용) ,통화코드값이 명시되어있지 않을 경우 KRW(원))
    private String exCode; // 해외주식 거래소 코드
}
