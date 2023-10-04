package com.iNine.resource.domain.stock;

import com.iNine.resource.domain.mydata.dto.CardDto;
import com.iNine.resource.domain.stock.dto.StockDto;
import com.iNine.resource.domain.stock.dto.StockRequestDto;
import com.iNine.resource.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
@Slf4j
public class StockController {
    private final StockService stockService;

    @GetMapping("/accounts")
    public Mono<StockDto.AccountInfoResponse> getAccountInfo(@RequestParam String orgCode,
                                                             @RequestParam String nextPage,
                                                             @RequestParam String limit,
                                                             Principal principal){
        String userId = principal.getName().toString();
        log.info("userId:{}", userId);
        return stockService.getAccountInfo(userId, orgCode, nextPage, limit).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @GetMapping("/accounts/all")
    public Mono<StockDto.MyAccountsResponse> getAllAccountInfo(Principal principal){
        String userId = principal.getName().toString();
//        String userId = "acrow0330@naver.com";
        log.info("userId:{}", userId);
        return stockService.getAllAccountInfo(userId).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @PostMapping("/accounts/detail")
    public Mono<StockDto.ProductInfoResponse> getProductInfo(Principal principal, @RequestBody StockRequestDto stockRequestDto){
        String userId = principal.getName().toString();
//        String userId = "acrow0330@naver.com";
        StockRequestDto.WebClientRequestBody webClientBody = stockRequestDto.getWebClientBody();
        webClientBody.setUser_id(userId);
        return stockService.getStockInfo(webClientBody).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @PostMapping("/all")
    public Mono<StockDto.InvestmentResponse> getProductInfo(Principal principal) {
        String userId = principal.getName().toString();
        log.info("userId:{}", userId);
//        String userId = "acrow0330@naver.com";
        return stockService.getAllSockInfo(userId).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @PostMapping("/invest/transRecord")
    public Mono<StockDto.TransactionResponse> getTransRecord(@RequestBody StockRequestDto.TransRecord requestBody) {
        return stockService.getTransRecord(requestBody).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @GetMapping("/invest/find/{keyWord}")
    public Mono<StockDto.OrgInfo> getOrgInfo(@PathVariable String keyWord) {
        return stockService.getOrgCode(keyWord).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }
}
