package com.iNine.resource.domain.exchange;

import com.iNine.resource.domain.exchange.dto.ExchangeDto;
import com.iNine.resource.domain.exchange.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ExchangeService {
    private final WebClient webClient;
    public ExchangeService(@Value("${url.host}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl+":8084")
                .build();
    }

//blocking 방법
//    public ExchangeDto.Data getExchangeInfo() {
//        ResponseEntity<ResponseDto<ExchangeDto.Data>> result = webClient.build().get().uri("/exchange")
//                .retrieve()
//                .toEntity(new ParameterizedTypeReference<ResponseDto<ExchangeDto.Data>>() {}).block();
//        ResponseDto<ExchangeDto.Data> response = result.getBody();
//        System.out.println(response.getCode());
//        return response.getData();
//    }

    //모든 은행과 환율 정보. 전체 정보
    public Mono<ExchangeDto.TotalResponse> getTotalExchangeInfo() {
        return webClient.get().uri("/exchange")
                .retrieve()
                .bodyToMono(ExchangeDto.TotalResponse.class);
    }

    //은행 코드 조회
    public Mono<ExchangeDto.BankResponse> getBankInfo() {
        return webClient.get().uri("/exchange/bank")
                .retrieve()
                .bodyToMono(ExchangeDto.BankResponse.class);
    }

    //특정 은행 환율 조회
    public Mono<ExchangeDto.ExcangeByBankCodeResponse> getBankExchangeInfo(String bankCode) {
        return webClient.get().uri("/exchange/bank/"+bankCode)
                .retrieve()
                .bodyToMono(ExchangeDto.ExcangeByBankCodeResponse.class);
    }

    //나라 코드 조회
    public Mono<ExchangeDto.CountryResponse> getCountryInfo() {
        return webClient.get().uri("/exchange/country")
                .retrieve()
                .bodyToMono(ExchangeDto.CountryResponse.class);
    }

    //특정 나라 환율 조회
    public Mono<ExchangeDto.ExchangeByCountryResponse> getCountryExchangeInfo(String countryCode) {
        return webClient.get().uri("/exchange/country/"+countryCode)
                .retrieve()
                .bodyToMono(ExchangeDto.ExchangeByCountryResponse.class);
    }

    //현금 살 때 특정 나라 환율 조회
    public Mono<ExchangeDto.BuyCountryExchangeResponse> getBuyCountryInfo(String countryCode) {
        return webClient.get().uri("/exchange/buy/"+countryCode)
                .retrieve()
                .bodyToMono(ExchangeDto.BuyCountryExchangeResponse.class);
    }

    //현금 팔 때 특정 나라 환율 조회
    public Mono<ExchangeDto.SellCountryExchangeResponse> getSellCountryInfo(String countryCode) {
        return webClient.get().uri("/exchange/sell/"+countryCode)
                .retrieve()
                .bodyToMono(ExchangeDto.SellCountryExchangeResponse.class);
    }

    //송금시 특정 나라 환율 조회
    public Mono<ExchangeDto.SellCountryExchangeResponse> getTransferCountryInfo(String countryCode) {
        return webClient.get().uri("/exchange/transfer/"+countryCode)
                .retrieve()
                .bodyToMono(ExchangeDto.SellCountryExchangeResponse.class);
    }
}
