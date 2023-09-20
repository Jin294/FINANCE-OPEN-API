package com.ssafy.iNine.FinancialAPI.cardtransaction.service;


import com.ssafy.iNine.FinancialAPI.card.repository.CardRepository;
import com.ssafy.iNine.FinancialAPI.cardtransaction.dto.CardTransactionDto;
import com.ssafy.iNine.FinancialAPI.cardtransaction.repository.CardTransactionRepository;

import com.ssafy.iNine.FinancialAPI.entity.Card;
import com.ssafy.iNine.FinancialAPI.entity.CardTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardTransactionService {

    private final CardTransactionRepository cardTransactionRepository;
    private final CardRepository cardRepository;

    // cardId로 거래내역 정보가 저장된게 있는지 찾고 없으면 거래내역 더미데이터 생성, 있으면 기존 거래내역 정보를 반환해줌
    public CardTransactionDto.CardTransactionResponseDto getTransactionList(CardTransactionDto.CardTransactionRequestDto  cardTransactionRequestDto) {

        Long cardId = cardTransactionRequestDto.getCardId();

        // cardId로 카드 정보 조회
        Card card = cardRepository.findById(cardId).orElse(null);

//        if (card == null) {
//            throw new CardNotFoundException("Card not found for cardId: " + cardId);
//        }

        // cardId로 카드 거래 내역 조회
        List<CardTransaction> transactionList = cardTransactionRepository.findByCard_CardId(cardId);
        // cardId로 카드 거래 내역 조회


        // 거래 내역 정보가 없으면 더미 데이터 생성
        if (transactionList == null || transactionList.isEmpty()) {
            transactionList = generateTransactionList(card);
            cardTransactionRepository.saveAll(transactionList);
        }

        // 페이지네이션
        Integer nextPage = cardTransactionRequestDto.getNextPage();
        Integer limit = cardTransactionRequestDto.getLimit();

        List<CardTransactionDto.CardTransactionDataDto> paginatedTransactionList = paginatedTransactionList(transactionList, nextPage, limit);

        // 응답 객체 생성
        CardTransactionDto.CardTransactionResponseDto transactionResponse = new CardTransactionDto.CardTransactionResponseDto();
        transactionResponse.setNextPage(nextPage);
        transactionResponse.setApprovedCnt(paginatedTransactionList.size());
        transactionResponse.setApprovedList(paginatedTransactionList);
        return transactionResponse;
    }


    private List<CardTransactionDto.CardTransactionDataDto> paginatedTransactionList(List<CardTransaction> transactionList, Integer nextPage, Integer limit) {
        int startIndex;

        if (nextPage != null) {
            // 이전 페이지에서 마지막으로 조회한 개체의 인덱스를 찾음
            startIndex = findLastIndex(transactionList, nextPage) + 1;
        } else {
            startIndex = 0;
        }

        int endIndex = Math.min(startIndex + limit, transactionList.size());

        // 페이지네이션된 거래내역 데이터 추출
        List<CardTransaction> paginatedTransaction = transactionList.subList(startIndex, endIndex);

        List<CardTransactionDto.CardTransactionDataDto> paginatedTransactionData = new ArrayList<>();
        for (CardTransaction transaction : paginatedTransaction) {

            paginatedTransactionData.add(CardTransactionDto.CardTransactionDataDto.of(transaction));
        }

        return paginatedTransactionData;
    }

    private int findLastIndex(List<CardTransaction> transactionList, Integer nextPage) {
        for (int i = 0; i < transactionList.size(); i++) {
            if (transactionList.get(i).getTransactionId().equals(nextPage)) {
                return i;
            }
        }
        return -1; // nextPage가 존재하지 않는 경우
    }

    public List<CardTransaction> generateTransactionList(Card card) {
        int transactionCnt = ThreadLocalRandom.current().nextInt(1, 101);

        List<CardTransaction> transactions = new ArrayList<>();

        for (int i = 0; i < transactionCnt; i++) {
            CardTransactionDto.CardTransactionDataDto transactionData = generateRandomTransaction();
            CardTransaction transaction = new CardTransaction();
            transaction.setCard(card);
            transaction.setApprovedNum(transactionData.getApprovedNum());
            transaction.setApprovedDtime(transactionData.getApprovedDtime());
            transaction.setStatus(transactionData.getStatus());
            transaction.setPayType(transactionData.getPayType());
            transaction.setTransDtime(transactionData.getTransDtime());
            transaction.setMerchantName(transactionData.getMerchantName());
            transaction.setMerchantRegno(transactionData.getMerchantRegno());
            transaction.setApprovedAmt(transactionData.getApprovedAmt());
            transaction.setModifiedAmt(transactionData.getModifiedAmt());
            transaction.setTotalInstallCnt(transactionData.getTotalInstallCnt());


            transactions.add(transaction);
        }
        return transactions;
    }

    private CardTransactionDto.CardTransactionDataDto generateRandomTransaction() {

        Long approvedNum = generateApprovedNum();
        Timestamp approvedDtime = generateApprovedDtime();
        String status = generateStatus();
        String payType = generatePayType();
        Timestamp transDtime = generateTransDtime();
        String merchantName = generateMerchantName();
        String merchantRegno = generateMerchantRegno();
        BigDecimal approvedAmt = generateApprovedAmt();
        BigDecimal modifiedAmt = generateModifiedAmt();
        Integer totalInstallCnt = generateTotalInstallCnt();

        return CardTransactionDto.CardTransactionDataDto.builder()
                .approvedNum(approvedNum)
                .approvedDtime(approvedDtime)
                .status(status)
                .payType(payType)
                .transDtime(transDtime)
                .merchantName(merchantName)
                .merchantRegno(merchantRegno)
                .approvedAmt(approvedAmt)
                .modifiedAmt(modifiedAmt)
                .totalInstallCnt(totalInstallCnt)
                .build();


    }

    private Long generateApprovedNum() {
        // 승인번호를 랜덤하게 생성 (예시로 6자리 랜덤 숫자 생성)
        return (long) ThreadLocalRandom.current().nextInt(100000, 1000000);
    }

    private Timestamp generateApprovedDtime() {
        // 승인일시를 랜덤하게 생성 (예시로 현재 시간에서 랜덤 시간 간격으로 생성)
        long currentTimeMillis = System.currentTimeMillis();
        long randomMillis = ThreadLocalRandom.current().nextLong(365 * 24 * 60 * 60 * 1000L); // 1년 이내의 밀리초
        return new Timestamp(currentTimeMillis - randomMillis);
    }

    private String generateStatus() {
        // 결제 상태 코드를 랜덤하게 생성 (01, 02, 03, 04 중 하나)
        String[] statusCodes = {"01", "02", "03", "04"};
        int randomIndex = ThreadLocalRandom.current().nextInt(statusCodes.length);
        return statusCodes[randomIndex];
    }

    private String generatePayType() {
        // 결제 유형 코드를 랜덤하게 생성 (01 또는 02 중 하나)
        String[] payTypeCodes = {"01", "02"};
        int randomIndex = ThreadLocalRandom.current().nextInt(payTypeCodes.length);
        return payTypeCodes[randomIndex];
    }

    private Timestamp generateTransDtime() {
        // 정정 또는 승인취소 일시를 랜덤하게 생성 (현재 시간에서 랜덤 시간 간격으로 생성)
        long currentTimeMillis = System.currentTimeMillis();
        long randomMillis = ThreadLocalRandom.current().nextLong(365 * 24 * 60 * 60 * 1000L); // 1년 이내의 밀리초
        return new Timestamp(currentTimeMillis - randomMillis);
    }

    private String generateMerchantName() {
        // 가맹점명을 랜덤하게 생성 (예시로 10글자의 랜덤 문자열 생성)
        StringBuilder merchantName = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char randomChar = (char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1);
            merchantName.append(randomChar);
        }
        return merchantName.toString();
    }

    private String generateMerchantRegno() {
        // 가맹점 사업자등록번호를 랜덤하게 생성 (예시로 12자리의 랜덤 숫자와 '-' 생성)
        StringBuilder merchantRegno = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            if (i == 3 || i == 6) {
                merchantRegno.append('-');
            } else {
                char randomDigit = (char) ThreadLocalRandom.current().nextInt('0', '9' + 1);
                merchantRegno.append(randomDigit);
            }
        }
        return merchantRegno.toString();
    }

    private BigDecimal generateApprovedAmt() {
        // 이용금액을 랜덤하게 생성 (예시로 1000부터 50000까지의 랜덤한 금액 생성)
        int randomAmt = ThreadLocalRandom.current().nextInt(1000, 50001);
        return new BigDecimal(randomAmt);
    }

    private BigDecimal generateModifiedAmt() {
        // 정정 후 금액을 랜덤하게 생성 (예시로 100부터 1000까지의 랜덤한 금액 생성)
        int randomAmt = ThreadLocalRandom.current().nextInt(100, 1001);
        return new BigDecimal(randomAmt);
    }

    private Integer generateTotalInstallCnt() {
        // 전체 할부 회차를 랜덤하게 생성 (예시로 1부터 12까지의 랜덤한 회차 생성)
        return ThreadLocalRandom.current().nextInt(1, 12);
    }

    }
