package com.ssafy.iNine.FinancialAPI.cardtransaction.controller;

import com.ssafy.iNine.FinancialAPI.cardtransaction.dto.CardTransactionDto;
import com.ssafy.iNine.FinancialAPI.cardtransaction.service.CardTransactionService;

import com.ssafy.iNine.FinancialAPI.common.response.DataResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardTransactionController {

    private final CardTransactionService cardTransactionService;

    @GetMapping("/cards-transaction")
    public DataResponse<CardTransactionDto.CardTransactionResponseDto> transactionList(
            @RequestParam("cardId") Long cardId,
            @RequestParam("orgCode") String orgCode,
            @RequestParam("fromDate") Timestamp fromDate,
            @RequestParam("toDate") Timestamp toDate,
            @RequestParam("nextPage") Integer nextPage,
            @RequestParam("limit") Integer limit
            ) {
        CardTransactionDto.CardTransactionRequestDto CardTransactionRequestDto = CardTransactionDto.CardTransactionRequestDto.builder()
                .cardId(cardId)
                .orgCode(orgCode)
                .fromDate(fromDate)
                .toDate(toDate)
                .nextPage(nextPage)
                .limit(limit)
                .build();


        CardTransactionDto.CardTransactionResponseDto result = cardTransactionService.getTransactionList(CardTransactionRequestDto);
        return new DataResponse<>(200, "카드 거래 내역 조회 성공", result);
    }
}
