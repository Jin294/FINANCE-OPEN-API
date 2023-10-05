package com.ssafy.iNine.FinancialAPI.card.controller;

import com.ssafy.iNine.FinancialAPI.card.dto.CardDto;
import com.ssafy.iNine.FinancialAPI.card.service.CardService;
import com.ssafy.iNine.FinancialAPI.common.response.DataResponse;
import com.ssafy.iNine.FinancialAPI.entity.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/cards")
    public DataResponse<CardDto.CardResponseDto> cardList(
            @RequestParam("orgCode") String orgCode,
            @RequestParam(value = "nextPage", required = false) String nextPage,
            @RequestParam("limit") Integer limit,
            @RequestParam("userId") String userId
    ) {
        CardDto.CardRequestDto cardRequestDto = CardDto.CardRequestDto.builder()
                .orgCode(orgCode)
                .nextPage(nextPage)
                .limit(limit)
                .userId(userId)
                .build();

        CardDto.CardResponseDto result = cardService.getUserCardList(cardRequestDto);
        return new DataResponse<>(200, "카드 목록 조회 성공", result);
    }

}
