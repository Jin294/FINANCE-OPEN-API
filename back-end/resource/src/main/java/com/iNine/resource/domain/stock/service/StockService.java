package com.iNine.resource.domain.stock.service;

import com.iNine.resource.domain.stock.dto.StockDto;
import com.iNine.resource.domain.stock.dto.StockRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class StockService {
    private final WebClient webClient;
    public StockService(@Value("${url.host}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl+":8082")
                .build();
    }

    //증권사 특정 증권사 계좌목록 조회
    public Mono<StockDto.AccountInfoResponse> getAccountInfo(String userId, String orgCode,
                                                             String nextPage, String limit) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/invest/accounts/"+userId)
                        .queryParam("orgCode", orgCode)
                        .queryParam("nextPage", nextPage)
                        .queryParam("limit", limit)
                        .build())
                .retrieve()
                .bodyToMono(StockDto.AccountInfoResponse.class);
    }

    //모든 증권사 계좌목록 조회
    public Mono<StockDto.MyAccountsResponse> getAllAccountInfo(String userId) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/invest/allAccounts/"+userId)
                        .build())
                .retrieve()
                .bodyToMono(StockDto.MyAccountsResponse.class);
    }

    //단일 계좌에 포함된 상품 목록 조회
    public Mono<StockDto.ProductInfoResponse> getStockInfo(StockRequestDto.WebClientRequestBody accountInfo) {
        return webClient.post().uri("/invest/accounts/detail")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(accountInfo)
                .retrieve()
                .bodyToMono(StockDto.ProductInfoResponse.class);
    }

    //모든 계좌에 포함된 상품 목록 조회
    public Mono<StockDto.InvestmentResponse> getAllSockInfo(String userId) {
        return webClient.post().uri(uriBuilder -> uriBuilder
                        .path("/invest/all/"+userId)
                        .build())
                .retrieve()
                .bodyToMono(StockDto.InvestmentResponse.class);
    }

    public Mono<StockDto.TransactionResponse> getTransRecord(StockRequestDto.TransRecord requestBody) {
        return webClient.post().uri("/invest/transRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(StockDto.TransactionResponse.class);
    }

    public Mono<StockDto.OrgInfo> getOrgCode(String keyWord) {
        return webClient.get().uri("/invest/find/"+keyWord)
                .retrieve()
                .bodyToMono(StockDto.OrgInfo.class);
    }
}
