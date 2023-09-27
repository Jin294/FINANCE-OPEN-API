package com.iNine.resource.domain.exchange.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExchangeDto {
    @Getter
    @Setter
    public static class TotalResponse {
        private int code;
        private String message;
        private BankAndExchange data;
    }

    @Getter
    @Setter
    public static class BankResponse {
        private int code;
        private String message;
        private List<Bank> data;
    }

    @Getter
    @Setter
    public static class ExcangeByBankCodeResponse {
        private int code;
        private String message;
        private List<ExchangeInfo> data;
    }

    @Getter
    @Setter
    public static class CountryResponse {
        private int code;
        private String message;
        private List<Country> data;
    }

    @Getter
    @Setter
    public static class ExchangeByCountryResponse {
        private int code;
        private String message;
        private List<ExchangeInfo> data;
    }

    @Getter
    @Setter
    public static class BuyCountryExchangeResponse {
        private int code;
        private String message;
        private List<BuyCountryExchange> data;
    }

    @Getter
    @Setter
    public static class SellCountryExchangeResponse {
        private int code;
        private String message;
        private List<SellCountryExchange> data;
    }

    @Getter
    @Setter
    public static class TransferResponse {
        private int code;
        private String message;
        private List<Transfer> data;
    }

    @Getter
    @Setter
    public static class BankAndExchange {
        private List<Bank> bank;
        private List<ExchangeInfoWithExchangeId> list;
    }

    @Getter
    @Setter
    public static class SellCountryExchange {
        private String bank;
        private String country;
        private double cashSellPrice;
        private double cashSellRate;
        private double price;
    }

    @Getter
    @Setter
    public static class BuyCountryExchange {
        private String bank;
        private String country;
        private double cashBuyPrice;
        private double cashBuyRate;
        private double price;
    }

    @Getter
    @Setter
    public static class Bank {
        private String code;
        private String name;
    }

    @Getter
    @Setter
    public static class Country {
        private String countryId;
        private String name;
    }

    @Getter
    @Setter
    public static class ExchangeInfoWithExchangeId {
        private Long exchangeId;
        private String bank;
        private String country;
        private Double cashBuyPrice;
        private Double cashBuyRate;
        private Double cashSellPrice;
        private Double cashSellRate;
        private Double transferPrice;
        private Double transferRate;
        private Double price;
    }

    @Getter
    @Setter
    public static class ExchangeInfo {
        private String bank;
        private String country;
        private Double cashBuyPrice;
        private Double cashBuyRate;
        private Double cashSellPrice;
        private Double cashSellRate;
        private Double transferPrice;
        private Double transferRate;
        private Double price;
    }

    @Getter
    @Setter
    public static class Transfer {
        private String bank;
        private String country;
        private Double transferPrice;
        private Double transferRate;
        private Double price;
    }
}
