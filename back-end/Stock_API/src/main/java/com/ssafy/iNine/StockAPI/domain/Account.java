package com.ssafy.iNine.StockAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "account")
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {
    private int userIdx; // 고객 번호
    private String firmCode; // 증권사코드

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accountNumber; // 계좌번호
    private String isConsent; // 전송요구 여부
    private String accountName; // 계좌명
    private int accountType; // 계좌종류
    private LocalDateTime issueDate; // 계좌개설일
    private boolean isTaxBenefits; // 세제혜택 적용여부

}
