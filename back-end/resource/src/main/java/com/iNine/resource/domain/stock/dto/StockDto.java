package com.iNine.resource.domain.stock.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StockDto {

    @Getter
    @Setter
    public static class CompanyListDto {
        CompanyInfo list;
    }

    @Getter
    @Setter
    public static class CompanyInfo {
        List<AccontInfo> 키움증권;
//        List<AccontInfo> 키움증미래에셋증권;
//        List<AccontInfo> 신한금융투자;
        List<AccontInfo> 토스증권;
        List<AccontInfo> 삼성증권;
    }

    @Getter
    @Setter
    public static class ProductInfoResponse {
        private LocalDateTime search_timestamp;
        private int next_page;
        private int prod_cnt;
        private int rsp_code;
        private int base_date;
        private List<ProductInfo> product_list;
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
    public static class AllAccountInfoResponse {
        private List<AccontInfo> myAccounts;
    }

    @Getter
    @Setter
    public static class AccountInfoResponse {
        private LocalDateTime search_timestamp;
        private int next_page;
        private int account_cnt;
        private int rsp_code;
        private List<AccontInfo> account_list;
        private String rsp_msg;
    }

    @Getter
    @Setter
    public static class AccontInfo {
        private String accountNumber;
        private String accountName;
        private String accountType;
        private LocalDateTime issueDate;
        private List<ProductInfo> productList;
        private Boolean consent;
        private Boolean taxBenefits;
    }
}
