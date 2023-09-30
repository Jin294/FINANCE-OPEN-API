package com.iNine.resource.domain.stock.service;

import com.iNine.resource.domain.stock.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class StockService {
    private final WebClient webClient;
    public StockService(@Value("${url.host}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl+":8082")
                .build();
    }

    //증권사 특정 증권사 계좌목록 조회
    public Mono<StockDto.AccountInfoResponse> getAccountInfo(String userId, String orgCode, String seachTimeStamp, String nextPage, String limit) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/invest/accounts/"+userId)
                        .queryParam("orgCode", orgCode)
                        .queryParam("seachTimeStamp", seachTimeStamp)
                        .queryParam("nextPage", nextPage)
                        .queryParam("limit", limit)
                        .build())
                .retrieve()
                .bodyToMono(StockDto.AccountInfoResponse.class);
    }

    //모든 증권사 계좌목록 조회
    public Mono<StockDto.AllAccountInfoResponse> getAllAccountInfo(String userId) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/invest/allAccounts/"+userId)
                        .build())
                .retrieve()
                .bodyToMono(StockDto.AllAccountInfoResponse.class);
    }

    //단일 계좌에 포함된 상품 목록 조회
    public Mono<StockDto.ProductInfoResponse> getStockInfo(String userId, String orgCode, String accountNum,
                                                             int searchTimeStamp,
                                                        int nextPage, int limit) {
        return webClient.post().uri(uriBuilder -> uriBuilder
                        .path("/invest/transRecord")
                        .queryParam("userId", userId)
                        .queryParam("orgCode", orgCode)
                        .queryParam("accountNum", accountNum)
                        .queryParam("searchTimeStamp", searchTimeStamp)
                        .queryParam("nextPage", nextPage)
                        .queryParam("limit", limit)
                        .build())
                .retrieve()
                .bodyToMono(StockDto.ProductInfoResponse.class);
    }

    //모든 계좌에 포함된 상품 목록 조회
    public Mono<StockDto.CompanyListDto> getAllSockInfo(String userId) {
        return webClient.post().uri(uriBuilder -> uriBuilder
                        .path("/invest/all/"+userId)
                        .build())
                .retrieve()
                .bodyToMono(StockDto.CompanyListDto.class);
    }

}
