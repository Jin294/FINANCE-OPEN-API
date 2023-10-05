package com.iNine.resource.domain.mydata.service;

import com.iNine.resource.domain.mydata.dto.CardDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MyDataService {
    private final WebClient webClient;
    public MyDataService(@Value("${url.host}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl+":8083")
                .build();
    }

    //보유한 카드 목록 조회
    public Mono<CardDto.CardInfoResponse> getUserCardInfo(String orgCode, String nextPage, Integer limit, String userId) {

        return webClient.get().uri(uriBuilder -> {
                    uriBuilder
                        .path("/cards")
                        .queryParam("orgCode", orgCode)
                        .queryParam("limit", limit)
                        .queryParam("userId", userId);
                        if(nextPage != null) {
                          uriBuilder.queryParam("nextPage", nextPage);
                        }
                        return uriBuilder.build();
                        })
                .retrieve()
                .bodyToMono(CardDto.CardInfoResponse.class);
    }

    //카드 거래 내역
    public Mono<CardDto.ApprovedInfoResponse> getCardTransactionInfo(Long cardId, String orgCode, String fromDate, String toDate, String nextPage, int limit) {

        return webClient.get().uri(uriBuilder -> {uriBuilder
                        .path("/cards/transaction")
                        .queryParam("cardId", cardId)
                        .queryParam("orgCode", orgCode)
                        .queryParam("fromDate", fromDate)
                        .queryParam("toDate", toDate)
                        .queryParam("limit", limit);
                        if(nextPage != null) {
                            uriBuilder.queryParam("nextPage", nextPage);
                        }
                        return uriBuilder.build();
                        })
                .retrieve()
                .bodyToMono(CardDto.ApprovedInfoResponse.class);
    }

    //소비내역 조회
    public Mono<CardDto.consumptionResponse> getUserConsumptionInfo(String userId) {

        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/consumption")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToMono(CardDto.consumptionResponse.class);
    }
}
