package com.iNine.resource.domain.stock.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class StockDto {

    @Getter
    @Setter
    public static class OrgInfo {
        private String org_name;
        private String org_code;
    }

    @Getter
    @Setter
    public static class TransactionResponse {
        private int rsp_code;
        private int trans_cnt;
        private String rsp_msg;
        private List<Transaction> trans_list;
    }

    @Getter
    @Setter
    public static class Transaction {
        private String prodName;
        private String prodCode;
        private String transDtime;
        private String transNo;
        private String transType;
        private String transTypeDetail;
        private double transNum;
        private double baseAmt;
        private double transAmt;
        private double settleAmt;
        private double balanceAmt;
        private String currencyCode;
        private String exCode;
    }

    @Getter
    @Setter
    public static class InvestmentResponse {
        private Map<String, List<AccountInfo>> list;
    }

    @Getter
    @Setter
    public static class ProductInfoResponse {
        private int search_timestamp;
        private int next_page;
        private int prod_cnt;
        private int rsp_code;
        private int base_date;
        private List<ProductInfo> prod_list;
        private String rsp_msg;
    }

    @Getter
    @Setter
    public static class ProductInfo {
        private String prodType;
        private String prodTypeDetail;
        private String prodCode;
        private String exCode;
        private String prodName;
        private String posType;
        private String creditType;
        private double purchaseAmt;
        private int holdingNum;
        private double evalAmt;
        private String currencyCode;
        private boolean taxBenefits;
    }

    @Getter
    @Setter
    public static class AccountInfoResponse {
        private String search_timestamp;
        private int next_page;
        private int account_cnt;
        private int rsp_code;
        private List<AccountInfo> account_list;
        private String rsp_msg;
    }

    @Getter
    @Setter
    public static class AccountInfo {
        private String accountNumber;
        private String accountName;
        private String accountType;
        private LocalDateTime issueDate;
        private List<ProductInfo> productList;
        private Boolean consent;
        private Boolean taxBenefits;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class MyAccountsResponse {
        private Map<String, List<Account>> myAccounts = new HashMap<>();

        @JsonAnySetter
        public void setMyAccount(String key, List<Account> value) {
            myAccounts.put(key, value);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class Account {
        private String accountNumber;
        private String accountName;
        private String accountType;
        private String issueDate;
        private List<String> productList;
        private boolean consent;
        private boolean taxBenefits;
    }
}
