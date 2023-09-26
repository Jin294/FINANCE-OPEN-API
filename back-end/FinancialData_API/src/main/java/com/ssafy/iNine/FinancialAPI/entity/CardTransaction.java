package com.ssafy.iNine.FinancialAPI.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Document(collection = "transaction")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "transactionId","dtime", "approvedNum", "approvedDtime", "status", "payType", "transDtime", "merchantId", "approvedAmt", "modifiedAmt", "totalInstallCnt" })
public class CardTransaction {
    @Id
    private String id; // 카드거래내역 고유 식별자

    private Timestamp dtime; // 정렬기준, approvedDtime 혹은 transDtime
    private Long approvedNum; // 승인번호
    private Timestamp approvedDtime; // 승인일시 금융소비자가 물품을 구매하거나 용역을 제공받은 날짜와 시간
    private String status; // 결제상태 코드 01: 승인, 02: 승인취소, 03: 정정, 04: 무승인매입
    private String payType; // 신용, 체크 구분 01: 신용, 02: 체크
    private Timestamp transDtime; // 정정 또는 승인취소 일시
    private Long merchantId;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal approvedAmt; // 이용금액
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal modifiedAmt; // 정정 후 금액, 결제 상태(코드)가 '03'인 경우만 회신
    private Integer totalInstallCnt; // 전체 할부 회차, 일시불일 경우 미회신

    @Indexed
    private Long cardId;

}