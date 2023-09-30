package com.iNine.resource.domain.mydata.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class CardDto {
    @Getter
    @Setter
    public static class CardInfoResponse {
        private int code;
        private String message;
        private CardInfoData data;
    }

    @Getter
    @Setter
    public static class CardInfoData {
        private String nextPage;
        private int cardCnt;
        private List<CardInfo> cardList;
    }

    @Getter
    @Setter
    public static class CardInfo {
        private int cardId;
        private String cardNum;
        private boolean isConsent;
        private String cardName;
        private int cardMember;
        private String cardType;
    }

    @Getter
    @Setter
    public static class ApprovedInfoResponse {
        private int code;
        private String message;
        private ApprovedInfoData data;
    }

    @Getter
    @Setter
    public static class ApprovedInfoData {
        private String nextPage;
        private int cardCnt;
        private List<ApprovedInfo> approvedList;
    }

    @Getter
    @Setter
    public static class ApprovedInfo {
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
    }

    @Getter
    @Setter
    public static class consumptionResponse {
        private int code;
        private String message;
        private List<consumptionInfo> data;
    }

    @Getter
    @Setter
    public static class consumptionInfo {
        private String merchantType;
        private BigDecimal price;
        private BigDecimal percent;
    }
}
