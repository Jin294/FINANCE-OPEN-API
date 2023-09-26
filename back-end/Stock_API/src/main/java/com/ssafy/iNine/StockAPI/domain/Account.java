package com.ssafy.iNine.StockAPI.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "invest_account")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {
    private String userId; // 고객 이메일
    private String firmCode; // 증권사코드

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID accountNumber; // 계좌번호
    private boolean isConsent; // 전송요구 여부
    private String accountName; // 계좌명
    private String accountType; // 계좌종류
    private LocalDateTime issueDate; // 계좌개설일
    private boolean isTaxBenefits; // 세제혜택 적용여부
    private double remainAmt = 10_000_000; // 현금 잔액 (초기금액 금 일천만원)
}
