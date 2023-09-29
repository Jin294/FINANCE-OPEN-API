package com.iNine.resource.domain.mydata;

import com.iNine.resource.domain.mydata.dto.CardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
@Slf4j
public class MyDataController {
    private final MyDataService myDataService;

    @GetMapping
    public Mono<CardDto.CardInfoResponse> getCardList(@RequestParam String orgCode, @RequestParam String nextPage,
                                                      @RequestParam int limit, Principal principal){
        String userId = principal.getName().toString();
        log.info("userId:{}", userId);
        return myDataService.getUserCardInfo(orgCode, nextPage, limit, userId).doOnSuccess(result -> {
                log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @GetMapping("/transaction")
    public Mono<CardDto.ApprovedInfoResponse> getCardTransactionInfo(@RequestParam Long cardId, @RequestParam String orgCode,
                                                                 @RequestParam String fromDate, @RequestParam String toDate,
                                                                 @RequestParam String nextPage, @RequestParam int limit){
        return myDataService.getCardTransactionInfo(cardId, orgCode, fromDate, toDate, nextPage, limit).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }

    @GetMapping("/consumption")
    public Mono<CardDto.consumptionResponse> getConsumptionInfo(Principal principal){
        String userId = principal.getName().toString();
        log.info("userId:{}", userId);
        return myDataService.getUserConsumptionInfo(userId).doOnSuccess(result -> {
                    log.info("result:{}", result);
                })
                .doOnError(error -> {
                    log.info("fail");
                });
    }
}
