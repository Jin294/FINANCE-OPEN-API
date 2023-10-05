package com.iNine.resource.domain.exchange;

import com.iNine.resource.domain.exchange.dto.ExchangeDto;
import com.iNine.resource.domain.exchange.dto.ResponseDto;
import com.ssafy.iNine.OAuth.common.response.DataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/exchange")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ExchangeController {
    private final ExchangeService exchangeService;
//블록킹 방법
//    @GetMapping
//    public com.ssafy.iNine.OAuth.common.response.DataResponse<?> getExchangeInfo() {
//        ExchangeDto.Data data = exchangeService.getExchangeInfo();
//
//        return new DataResponse<>(200, "success", data);
//    }
    //전체 환율 정보
    @GetMapping
    public Mono<ExchangeDto.TotalResponse> getExchangeInfo() {
        return exchangeService.getTotalExchangeInfo()
                .doOnSuccess(result -> {
                    log.info("exchange result:{}", result);
                })
                .doOnError(error -> {
                    log.info("exchange fail");
                });
    }

    //은행 코드 조회
    @GetMapping("/bank")
    public Mono<ExchangeDto.BankResponse> getBankInfo() {
        return exchangeService.getBankInfo()
                .doOnSuccess(result -> {
                    log.info("exchange result:{}", result);
                })
                .doOnError(error -> {
                    log.info("exchange fail");
                });
    }

    //특정 은행 환율 조회
    @GetMapping("/bank/{bankCode}")
    public Mono<ExchangeDto.ExcangeByBankCodeResponse> getBankExchangeInfo(@PathVariable String bankCode) {
        return exchangeService.getBankExchangeInfo(bankCode)
                .doOnSuccess(result -> {
                    log.info("exchange result:{}", result);
                })
                .doOnError(error -> {
                    log.info("exchange fail");
                });
    }

    //나라 코드 조회
    @GetMapping("/country")
    public Mono<ExchangeDto.CountryResponse> getCountryInfo() {
        return exchangeService.getCountryInfo()
                .doOnSuccess(result -> {
                    log.info("exchange result:{}", result);
                })
                .doOnError(error -> {
                    log.info("exchange fail");
                });
    }

    //특정 나라 환율 조회
    @GetMapping("/country/{countryCode}")
    public Mono<ExchangeDto.ExchangeByCountryResponse> getCountryExchangeInfo(@PathVariable String countryCode) {
        return exchangeService.getCountryExchangeInfo(countryCode)
                .doOnSuccess(result -> {
                    log.info("exchange result:{}", result);
                })
                .doOnError(error -> {
                    log.info("exchange fail");
                });
    }

    //현긍 살 때 특정 나라 환율 조회
    @GetMapping("/buy/{countryCode}")
    public Mono<ExchangeDto.BuyCountryExchangeResponse> getBuyCountryInfo(@PathVariable String countryCode) {
        return exchangeService.getBuyCountryInfo(countryCode)
                .doOnSuccess(result -> {
                    log.info("exchange result:{}", result);
                })
                .doOnError(error -> {
                    log.info("exchange fail");
                });
    }

    //현금 팔 때 특정 나라 환율 조회
    @GetMapping("/sell/{countryCode}")
    public Mono<ExchangeDto.SellCountryExchangeResponse> getSellCountryInfo(@PathVariable String countryCode) {
        return exchangeService.getSellCountryInfo(countryCode)
                .doOnSuccess(result -> {
                    log.info("exchange result:{}", result);
                })
                .doOnError(error -> {
                    log.info("exchange fail");
                });
    }

    //송금시 특정 나라 환율 조회
    @GetMapping("/transfer/{countryCode}")
    public Mono<ExchangeDto.SellCountryExchangeResponse> getTransferCountryInfo(@PathVariable String countryCode) {
        return exchangeService.getTransferCountryInfo(countryCode)
                .doOnSuccess(result -> {
                    log.info("exchange result:{}", result);
                })
                .doOnError(error -> {
                    log.info("exchange fail");
                });
    }
}
