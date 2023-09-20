package com.ssafy.iNine.StockAPI.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockAccountDto {
    private String accountNumber; // 계좌번호
    private String isConsent; // 전송요구 여부
    private String accountName; // 계좌명
    private int accountType; // 계좌종류
    private LocalDateTime issueDate; // 계좌개설일
    private boolean isTaxBenefits; // 세제혜택 적용여부

    private String firmCode; // 증권사코드
    private int userIdx; // 고객 번호

    @Override
    public String toString() {
        return "계좌 정보{" +
                "account_num='" + accountNumber + '\'' +
                ", is_consent='" + isConsent + '\'' +
                ", account_name='" + accountName + '\'' +
                ", account_type=" + accountType +
                ", issue_date=" + issueDate +
                ", is_tax_benefits=" + isTaxBenefits +
                '}';
    }
}
