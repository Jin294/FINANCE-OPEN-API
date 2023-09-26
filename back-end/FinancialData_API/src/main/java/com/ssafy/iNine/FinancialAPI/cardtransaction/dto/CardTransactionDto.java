package com.ssafy.iNine.FinancialAPI.cardtransaction.dto;

import com.ssafy.iNine.FinancialAPI.entity.CardTransaction;
import com.ssafy.iNine.FinancialAPI.entity.Merchant;
import lombok.*;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


public class CardTransactionDto {


    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardTransactionRequestDto {
        private Long cardId;
        private String orgCode;
        private Timestamp fromDate;
        private Timestamp toDate;
        private String nextPage;
        private Integer limit;
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardTransactionDataDto {

        private String transactionId;
        private Long approvedNum;
        private Timestamp approvedDtime;
        private String status;
        private String payType;
        private Timestamp transDtime;
        private String merchantName;
        private String merchantRegno;
        private BigDecimal approvedAmt;
        private BigDecimal modifiedAmt;
        private Integer totalInstallCnt;

        private Long cardId;

        public static CardTransactionDataDto of(CardTransaction transaction, Merchant merchant) {
            return CardTransactionDataDto.builder()
                    .transactionId(transaction.getId())
                    .approvedNum(transaction.getApprovedNum())
                    .approvedDtime(transaction.getApprovedDtime())
                    .status(transaction.getStatus())
                    .payType(transaction.getPayType())
                    .transDtime(transaction.getTransDtime())
                    .merchantName(merchant.getMerchantName())
                    .merchantRegno(merchant.getMerchantRegno())
                    .approvedAmt(transaction.getApprovedAmt())
                    .modifiedAmt(transaction.getModifiedAmt())
                    .totalInstallCnt(transaction.getTotalInstallCnt())
                    .cardId(transaction.getCardId())
                    .build();
        }
    }


    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardTransactionResponseDto {
        private String nextPage;
        private Integer approvedCnt;
        private List<CardTransactionDataDto> approvedList;
    }

    public static CardTransactionResponseDto of(String nextPage, Integer approvedCnt, List<CardTransactionDataDto> approvedList) {
        return CardTransactionResponseDto.builder()
                .nextPage(nextPage)
                .approvedCnt(approvedCnt)
                .approvedList(approvedList)
                .build();
    }


}
