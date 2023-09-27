package com.iNine.resource.domain.mydata.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    @Getter
    @Setter
    public static class CardInfoResponse {
        private int nextPage;
        private int cardCnt;
    }

    @Getter
    @Setter
    public static class CardInfo {
        private int cardId;
        private int cardNum;
        private boolean isConsent;
        private String cardName;
        private String cardMember;

    }
}
