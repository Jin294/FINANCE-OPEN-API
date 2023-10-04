package com.ssafy.iNine.StockAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @JsonProperty("prod_type")
    private String prodType; // 상품종류 (코드)
    @JsonProperty("prod_type_detail")
    private String prodTypeDetail; // 상품종류 상세 (정보제공자가 특정한 상품의 상세 명칭(CMA, RP, CD, ETF)
    @JsonProperty("prod_code")
    private String prodCode; // 종목코드 (상품코드)
    @JsonProperty("ex_code")
    private String exCode; // 해외주식 거래소 코드(해외주식 한정)
    @JsonProperty("prod_name")
    private String prodName; // 종목명 (해당 계좌에 보유하고 있는 상품명칭)
    @JsonProperty("pos_type")
    private String posType; // 파생상품 포지션구분 (코드) / 01 : 매수, 02 : 매도
    @JsonProperty("credit_type")
    private String creditType; // 신용구분 (코드) / 01 : 현금, 02 : 신용, 03 : 담보대출
    @JsonProperty("is_tax_benefits")
    private boolean isTaxBenefits; // 세제혜택 적용여부
    @JsonProperty("purchase_amt")
    private double purchaseAmt; // 매입금액 (조회시점 기준 해당 계좌 보유상품별 총 매입금액)
    @JsonProperty("holding_num")
    private long holdingNum; // 보유수량 (조회시점 기준 해당 계좌 부유상품별 수량)
    @JsonProperty("eval_amt")
    private double evalAmt; // 전일 종가 기준 해당 상품의 평가금액 (= 전일 종가 * 보유수량)
    @JsonProperty("currency_code")
    private String currencyCode; // 통화코드 (해당 상품에 적용된 통화코드)
}
