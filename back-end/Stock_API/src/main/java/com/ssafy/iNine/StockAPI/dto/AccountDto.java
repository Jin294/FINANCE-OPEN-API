package com.ssafy.iNine.StockAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @JsonProperty("account_number")
    private String accountNumber; // 계좌번호

    @JsonProperty("is_consent")
    private boolean isConsent; // 전송요구 여부

    @JsonProperty("account_name")
    private String accountName; // 계좌명

    @JsonProperty("account_type")
    private String accountType; // 계좌종류

    @JsonProperty("issue_date")
    private LocalDateTime issueDate; // 계좌개설일

    @JsonProperty("is_tax_benefits")
    private boolean isTaxBenefits; // 세제혜택 적용여부

    @JsonProperty("product_list")
    private List<ProductDto> productList;
}
