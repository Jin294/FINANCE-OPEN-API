package com.iNine.resource.domain.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StockRequestDto {
    private String orgCode;
    private String accountNum;
    private String nextPage;
    private String limit;

    @Getter
    @Setter
    public static class detail {
        private String orgCode;
        private String accountNum;
        private String nextPage;
        private String limit;
    }

    @Getter
    @Setter
    public static class TransRecord {
        private String orgCode;
        private String accountNum;
        private String fromDate;
        private String toDate;
        private String nextPage;
        private String limit;
    }

    public WebClientRequestBody getWebClientBody() {
       WebClientRequestBody webClientRequestBody = new WebClientRequestBody();
       webClientRequestBody.setLimit(this.limit);
       webClientRequestBody.setNextPage(this.nextPage);
       webClientRequestBody.setAccountNum(this.accountNum);
       webClientRequestBody.setOrgCode(this.orgCode);

       return webClientRequestBody;
    }

    @Getter
    @Setter
    public static class WebClientRequestBody {
        private String orgCode;
        private String accountNum;
        private String nextPage;
        private String limit;
        private String userId;
    }
}
