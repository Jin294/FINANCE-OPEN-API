package com.iNine.resource.domain.stock;

import com.iNine.resource.domain.mydata.dto.CardDto;
import com.iNine.resource.domain.stock.dto.StockDto;
import com.iNine.resource.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
@Slf4j
public class StockController {
    private final StockService stockService;

    @GetMapping("/account")
    public Mono<StockDto.AccountInfoResponse> getAccountInfo(@RequestParam String orgCode,
                                                             @RequestParam String searchTimeStamp,
                                                             @RequestParam String nextPage,
                                                             @RequestParam String limit, Principal principal){
        String userId = principal.getName().toString();
        log.info("userId:{}", userId);
        return stockService.getAccountInfo(userId, orgCode, searchTimeStamp, nextPage, limit).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @GetMapping("/account/all")
    public Mono<StockDto.AllAccountInfoResponse> getAllAccountInfo(Principal principal){
        String userId = principal.getName().toString();
        log.info("userId:{}", userId);
        return stockService.getAllAccountInfo(userId).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @GetMapping("/product")
    public Mono<StockDto.ProductInfoResponse> getProductInfo(@RequestParam String orgCode,
                                                             @RequestParam int searchTimeStamp,
                                                             @RequestParam String accountNum,
                                                             @RequestParam int nextPage,
                                                             @RequestParam int limit,
                                                             Principal principal){
        String userId = principal.getName().toString();
        log.info("userId:{}", userId);
        return stockService.getStockInfo(userId, orgCode, accountNum, searchTimeStamp, nextPage, limit).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @GetMapping("/product/all")
    public Mono<StockDto.CompanyListDto> getProductInfo(Principal principal){
        String userId = principal.getName().toString();
        log.info("userId:{}", userId);
        return stockService.getAllSockInfo(userId).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }
}
