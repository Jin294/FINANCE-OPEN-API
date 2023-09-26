package com.ssafy.iNine.FinancialAPI.exchange.controller;

import com.ssafy.iNine.FinancialAPI.common.response.CommonResponse;
import com.ssafy.iNine.FinancialAPI.common.response.DataResponse;
import com.ssafy.iNine.FinancialAPI.exchange.dto.BankDto;
import com.ssafy.iNine.FinancialAPI.exchange.dto.CountryDto;
import com.ssafy.iNine.FinancialAPI.exchange.dto.ExchangeDto;
import com.ssafy.iNine.FinancialAPI.exchange.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@Slf4j
@RestController
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping("/exchange")
    public DataResponse<Map<String, Object>> exchange() throws IOException {
        Map<String, Object> exchangeList = exchangeService.getExchangeList();
        return new DataResponse<Map<String, Object>>(200, "환율 정보 조회 성공", exchangeList);
    }

    @GetMapping("/exchange/bank")
    public DataResponse<List<BankDto>> exchangeBankList() throws IOException {
        List<BankDto> bankList = exchangeService.getBankList();
        return new DataResponse<>(200, "은행 코드 조회 성공", bankList);
    }

    @GetMapping("/exchange/bank/{bankCode}")
    public DataResponse<List<ExchangeDto.All>> exchangeBank(@PathVariable String bankCode) throws IOException {
        List<ExchangeDto.All> exchangeList = exchangeService.getExchangeBankList(bankCode);
        return new DataResponse<List<ExchangeDto.All>>(200, "은행 환율 정보 조회 성공", exchangeList);
    }

    @GetMapping("/exchange/country")
    public DataResponse<List<CountryDto>> getCountry() throws IOException {
        List<CountryDto> countryList = exchangeService.getCountryList();
        return new DataResponse<>(200, "나라 조회 성공", countryList);
    }

    @GetMapping("/exchange/country/{countryId}")
    public DataResponse<List<ExchangeDto.All>> getCountryExchange(@PathVariable Long countryId) throws IOException {
        List<ExchangeDto.All> exchangeList = exchangeService.getCountryExchange(countryId);
        return new DataResponse<List<ExchangeDto.All>>(200, "나라별 환율 조회 성공", exchangeList);
    }

    @GetMapping("/exchange/buy/{countryId}")
    public DataResponse<List<ExchangeDto.Buy>> getBuyCountryExchange(@PathVariable Long countryId) throws IOException {
        List<ExchangeDto.Buy> exchangeList = exchangeService.getBuyCountryExchange(countryId);
        return new DataResponse<List<ExchangeDto.Buy>>(200, "은행별 나라 살 때 환율 조회 성공", exchangeList);
    }

    @GetMapping("/exchange/sell/{countryId}")
    public DataResponse<List<ExchangeDto.Sell>> getSellCountryExchange(@PathVariable Long countryId) throws IOException {
        List<ExchangeDto.Sell> exchangeList = exchangeService.getSellCountryExchange(countryId);
        return new DataResponse<List<ExchangeDto.Sell>>(200, "은행별 나라 팔 때 환율 조회 성공", exchangeList);
    }

    @GetMapping("/exchange/transfer/{countryId}")
    public DataResponse<List<ExchangeDto.Transfer>> getTransferCountryExchange(@PathVariable Long countryId) throws IOException {
        List<ExchangeDto.Transfer> exchangeList = exchangeService.getTransferCountryExchange(countryId);
        return new DataResponse<List<ExchangeDto.Transfer>>(200, "은행별 나라 송금할 때 환율 조회 성공", exchangeList);
    }

    //================================테스트용=============================

    @GetMapping("/exchange/crawling")
    public DataResponse<List<List<ExchangeDto.All>>> crawlingExchange() throws IOException {
        List<List<ExchangeDto.All>> exchangeDtoList = exchangeService.getMyBank();
        return new DataResponse<>(200, "환율 정보 크롤링 성공", exchangeDtoList);
    }

    @GetMapping("/exchange/save")
    public CommonResponse writeExchange() throws IOException {
        exchangeService.insertExchange();
        return new CommonResponse(200, "환율 정보 저장 성공");
    }

    @GetMapping("/exchange/update")
    public CommonResponse modifyExchange() throws IOException {
        exchangeService.updateExchange();
        return new CommonResponse(200, "환율 정보 업데이트 성공");
    }
}
