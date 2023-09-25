package com.ssafy.iNine.StockAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "invest_product")
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    private String prodType; // 상품종류 (코드)
    private String prodTypeDetail; // 상품종류 상세 (정보제공자가 특정한 상품의 상세 명칭(CMA, RP, CD, ETF)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String prodCode; // 종목코드 (상품코드)

    @Nullable
    private String exCode; // 해외주식 거래소 코드(해외주식 한정)
    private String prodName; // 종목명 (해당 계좌에 보유하고 있는 상품명칭
    private String currencyCode; // 통화코드 (해당 상품에 적용된 통화코드)
}
