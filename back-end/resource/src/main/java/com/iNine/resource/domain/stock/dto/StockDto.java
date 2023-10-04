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
        private String prod_name;
        private String prod_code;
        private String trans_dtime;
        private String trans_no;
        private String trans_type;
        private String trans_type_detail;
        private double trans_num;
        private double base_amt;
        private double trans_amt;
        private double settle_amt;
        private double balance_amt;
        private String currency_code;
        private String ex_code;
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
        private String prod_type;
        private String prod_type_detail;
        private String prod_code;
        private String ex_code;
        private String prod_name;
        private String pos_type;
        private String credit_type;
        private double purchase_amt;
        private int holding_num;
        private double eval_amt;
        private String currency_code;
        private boolean is_tax_benefits;
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
        private String account_number;
        private String account_name;
        private String account_type;
        private LocalDateTime issue_date;
        private List<ProductInfo> product_list;
        private Boolean is_consent;
        private Boolean is_tax_benefits;
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
        private String account_number;
        private String account_name;
        private String account_type;
        private String issue_date;
        private List<String> product_list;
        private boolean is_consent;
        private boolean is_tax_benefits;
    }
}
