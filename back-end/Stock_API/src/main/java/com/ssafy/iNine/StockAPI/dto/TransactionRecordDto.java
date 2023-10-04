package com.ssafy.iNine.StockAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TransactionRecordDto {
    @JsonProperty("prod_name")
    private String prodName; // 종목명 (상품명, 거래가 발생한 상품의 명칭)

    @JsonProperty("prod_code")
    private String prodCode; // 종목코드 (상품코드)

    @JsonProperty("trans_dtime")
    private LocalDateTime transDtime; // 거래일시 또는 거래일자

    @JsonProperty("trans_no")
    private String transNo; // 거래번호 (해당 일자의 거래 특정 번호, 없을 경우 미회신)

    @JsonProperty("trans_type")
    private String transType; // 거래종류 (거래종류 특정코드)

    @JsonProperty("trans_type_detail")
    private String transTypeDetail; // 상세 거래 종류 (코드가 아닌 상세 거래 종류 명)

    @JsonProperty("trans_num")
    private double transNum; // 거래수량 (해당종목이 본 거래에서 거래된 총 수량)

    @JsonProperty("base_amt")
    private double baseAmt; // 거래단가 (해당종목이 본 거래에서 거래된 단위 가격)

    @JsonProperty("trans_amt")
    private double transAmt; // 거래금액 (해당종목이 본 거래에서 거래된 총 금액)

    @JsonProperty("settle_amt")
    private double settleAmt; // 정산금액 (거래대금에서 제세공과금 등을 차감한 금액)

    @JsonProperty("balance_amt")
    private double balanceAmt; // 거래후잔액 (해당 계좌의 현금 잔액(예수금))

    @JsonProperty("currency_code")
    private String currencyCode; // 거래에 적용된 통화코드 (통화코드값이 명시되어있지 않을 경우 "KRW"(원))

    @JsonProperty("ex_code")
    private String exCode; // 해외주식 거래소 코드
}
