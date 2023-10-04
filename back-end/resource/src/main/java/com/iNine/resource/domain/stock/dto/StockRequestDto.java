package com.iNine.resource.domain.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class StockRequestDto {
    private String org_code;
    private String account_num;
    private String next_page;
    private String limit;

    @Getter
    @Setter
    public static class detail {
        private String org_code;
        private String account_num;
        private String next_page;
        private String limit;
    }

    @Getter
    @Setter
    public static class TransRecord {
        private String org_code;
        private String account_num;
        private String from_date;
        private String to_date;
        private String next_page;
        private String limit;
    }

    public WebClientRequestBody getWebClientBody() {
       WebClientRequestBody webClientRequestBody = new WebClientRequestBody();
       webClientRequestBody.setLimit(this.limit);
       webClientRequestBody.setNext_page(this.next_page);
       webClientRequestBody.setAccount_num(this.account_num);
       webClientRequestBody.setOrg_code(this.org_code);

       return webClientRequestBody;
    }

    @Getter
    @Setter
    public static class WebClientRequestBody {
        private String org_code;
        private String account_num;
        private String next_page;
        private String limit;
        private String user_id;
    }
}
