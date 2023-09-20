package com.ssafy.iNine.FinancialAPI.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@ToString(of = {"transactionId", "approvedNum", "approvedDtime", "status", "payType", "transDtime", "merchantName", "merchantRegno", "approvedAmt", "modifiedAmt", "totalInstallCnt" })
public class CardTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId; // 카드거래내역 고유 식별자(자동증가)

    private Long approvedNum; // 승인번호
    private Timestamp approvedDtime; // 승인일시 금융소비자가 물품을 구매하거나 용역을 제공받은 날짜와 시간
    private String status; // 결제상태 코드 01: 승인, 02: 승인취소, 03: 정정, 04: 무승인매입
    private String payType; // 신용, 체크 구분 01: 신용, 02: 체크
    private Timestamp transDtime; // 정정 또는 승인취소 일시
    private String merchantName; // 가맹점명
    private String merchantRegno; // 가맹점 사업자번호 "-"포함 NS(12)
    private BigDecimal approvedAmt; // 이용금액
    private BigDecimal modifiedAmt; // 정정 후 금액, 결제 상태(코드)가 '03'인 경우만 회신
    private Integer totalInstallCnt; // 전체 할부 회차, 일시불일 경우 미회신

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardId") // 외래 키로 사용할 카드의 식별자
    private Card card;



}
