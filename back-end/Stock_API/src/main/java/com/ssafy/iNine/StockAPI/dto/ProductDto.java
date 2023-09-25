package com.ssafy.iNine.StockAPI.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String prodType; // 상품종류 (코드)
    private String prodTypeDetail; // 상품종류 상세 (정보제공자가 특정한 상품의 상세 명칭(CMA, RP, CD, ETF)
    private String prodCode; // 종목코드 (상품코드)
    private String exCode; // 해외주식 거래소 코드(해외주식 한정)
    private String prodName; // 종목명 (해당 계좌에 보유하고 있는 상품명칭)
    private String posType; // 파생상품 포지션구분 (코드) / 01 : 매수, 02 : 매도
    private String creditType; // 신용구분 (코드) / 01 : 현금, 02 : 신용, 03 : 담보대출
    private boolean isTaxBenefits; // 세제혜택 적용여부
    private double purchaseAmt; // 매입금액 (조회시점 기준 해당 계좌 보유상품별 총 매입금액)
    private long holdingNum; // 보유수량 (조회시점 기준 해당 계좌 부유상품별 수량)
    private double evalAmt; // 전일 종가 기준 해당 상품의 평가금액 (= 전일 종가 * 보유수량)
    private String currencyCode; // 통화코드 (해당 상품에 적용된 통화코드)
}
