package com.ssafy.iNine.FinancialAPI.cardtransaction.service;


import com.ssafy.iNine.FinancialAPI.card.repository.CardRepository;
import com.ssafy.iNine.FinancialAPI.cardtransaction.dto.CardTransactionDto;
import com.ssafy.iNine.FinancialAPI.cardtransaction.repository.CardTransactionRepository;

import com.ssafy.iNine.FinancialAPI.cardtransaction.repository.MerchantRepository;
import com.ssafy.iNine.FinancialAPI.common.exception.CommonException;
import com.ssafy.iNine.FinancialAPI.common.exception.ExceptionType;
import com.ssafy.iNine.FinancialAPI.entity.Card;
import com.ssafy.iNine.FinancialAPI.entity.CardTransaction;
import com.ssafy.iNine.FinancialAPI.entity.Merchant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardTransactionService {

    private final CardTransactionRepository cardTransactionRepository;
    private final CardRepository cardRepository;
    private final MerchantRepository merchantRepository;

    // cardId로 거래내역 정보가 저장된게 있는지 찾고 없으면 거래내역 더미데이터 생성, 있으면 기존 거래내역 정보를 반환
    public CardTransactionDto.CardTransactionResponseDto getTransactionList(CardTransactionDto.CardTransactionRequestDto cardTransactionRequestDto) {
        Long cardId = cardTransactionRequestDto.getCardId();
        Timestamp fromDate = cardTransactionRequestDto.getFromDate();
        Timestamp toDate = cardTransactionRequestDto.getToDate();
        CardTransactionDto.CardTransactionResponseDto transactionResponse = new CardTransactionDto.CardTransactionResponseDto();

        // cardId로 카드 정보 조회
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new CommonException(ExceptionType.CARD_NOT_FOUND));

        // cardId로 카드 거래 내역 조회 (fromDate와 toDate 사이의 거래만 조회)
        List<CardTransaction> transactionList = cardTransactionRepository.findAllByCardIdAndDtimeBetweenOrderByDtimeAsc(cardId, fromDate, toDate);

        // 거래 내역 정보가 없으면 더미 데이터 생성
        if (transactionList == null || transactionList.isEmpty()) {
            transactionList = generateTransactionList(card);
            cardTransactionRepository.saveAll(transactionList);
            transactionList = cardTransactionRepository.findAllByCardIdAndDtimeBetweenOrderByDtimeAsc(cardId, fromDate, toDate);
        }


        // 페이지네이션
        String nextPage = cardTransactionRequestDto.getNextPage();
        Integer limit = cardTransactionRequestDto.getLimit();


        List<CardTransactionDto.CardTransactionDataDto> paginatedTransactionList = paginatedTransaction(transactionList, nextPage, limit);

        // paginatedTransactionList의 마지막 값의 id와 transactionList 중 일치하는 id의 다음 id를 가져와서 nextPage로 세팅
        CardTransactionDto.CardTransactionDataDto lastPaginatedTransaction = paginatedTransactionList.get(paginatedTransactionList.size() -1);

        for(int i = 0; i<transactionList.size(); i++ ) {
            transactionList.get(i);
            if (lastPaginatedTransaction.getTransactionId().equals(transactionList.get(i).getId())) {

                if (i + 1 < transactionList.size()) {
                    transactionResponse.setNextPage(transactionList.get(i + 1).getId());
                }

            }

        }

        // 응답 객체 생성

//        transactionResponse.setNextPage(nextPage);
        transactionResponse.setApprovedCnt(paginatedTransactionList.size());
        transactionResponse.setApprovedList(paginatedTransactionList);
        return transactionResponse;
    }


    private List<CardTransactionDto.CardTransactionDataDto> paginatedTransaction(List<CardTransaction> transactionList, String nextPage, Integer limit) {
        int startIndex = -1;

        if (!nextPage.equals("")) {
            CardTransaction transaction = cardTransactionRepository.findById(nextPage)
                    .orElseThrow(() -> new CommonException(ExceptionType.CARD_TRANSACTION_NOT_FOUND));

//            CardTransaction nextTransaction = cardTransactionRepository.findFirstByDtimeAfterOrderByDtimeAsc(prevTransaction.getDtime());
            System.out.println(transaction);
            // 이전 페이지에서 마지막으로 조회한 개체의 인덱스를 찾음
            for (int i = 0; i < transactionList.size(); i++) {
                if (transactionList.get(i).getId().equals(transaction.getId())) {
                    startIndex = i;
                }
            }


            if (startIndex == -1) return new ArrayList<>();

        } else {
            startIndex = 0;
        }

        int endIndex = Math.min(startIndex + limit, transactionList.size());


        // 페이지네이션된 거래내역 데이터 추출
        List<CardTransaction> paginatedTransaction = transactionList.subList(startIndex, endIndex);

        List<CardTransactionDto.CardTransactionDataDto> paginatedTransactionData = new ArrayList<>();
        for (CardTransaction transaction : paginatedTransaction) {
            Merchant merchant = merchantRepository.findById(transaction.getMerchantId())
                    .orElseThrow(() -> new CommonException(ExceptionType.MERCHANT_NOT_FOUND));
            paginatedTransactionData.add(CardTransactionDto.CardTransactionDataDto.of(transaction, merchant));
        }

        return paginatedTransactionData;
    }


    public List<CardTransaction> generateTransactionList(Card card) {
        int transactionCnt = ThreadLocalRandom.current().nextInt(50, 151);

        List<CardTransaction> transactions = new ArrayList<>();

        for (int i = 0; i < transactionCnt; i++) {
            CardTransaction transaction = new CardTransaction();
            transaction.setCardId(card.getCardId());

            String status = generateStatus();
            transaction.setStatus(status);

            Timestamp approvedDtime = null;

            // 02, 03인 경우에 승인내역 status가 01이고 나머지는 동일한 거래 내역을 만든다


            if (status.equals("01")) {

                approvedDtime = generateApprovedDtime();
                transaction.setApprovedNum(generateApprovedNum());
            } else {


                approvedDtime = generateApprovedDtime();
                Timestamp transDtime = generateTransDtime(approvedDtime);
                transaction.setTransDtime(transDtime);


                if (transaction.getStatus().equals("02")) {
                    // status가 01인 거래 내역을 하나 더 생성한다. status 외 나머지 필드는 status가 02인 것과 같다.
                    CardTransaction newTransaction = new CardTransaction();
                    newTransaction.setStatus("01");
                    newTransaction.setId(transaction.getId());
                    newTransaction.setApprovedNum(transaction.getApprovedNum());
                    newTransaction.setApprovedDtime(transaction.getApprovedDtime());


                }


                if (transaction.getStatus().equals("03")) {
                    //status가 01인 거래 내역을 하나 더 생성한다. status외 나머지 필드는 status가 02인 것과 같다.


                    if (approvedDtime.before(transDtime)) {
                        transaction.setApprovedNum(generateApprovedNum());
                    } else {

                        transaction.setApprovedNum(generateApprovedNum());
                        approvedDtime = new Timestamp(transDtime.getTime() - 1000000);
                    }

                    transaction.setModifiedAmt(generateModifiedAmt());
                }
            }

            transaction.setApprovedDtime(approvedDtime);

            transaction.setDtime(approvedDtime);


            if (card.getCardType().equals("01")) {
                transaction.setPayType("01");
            } else {
                transaction.setPayType("02");
            }

            if (transaction.getPayType().equals("01")) {
                transaction.setTotalInstallCnt(generateTotalInstallCnt());
            }

            transaction.setMerchantId(ThreadLocalRandom.current().nextLong(1, merchantRepository.count()));
            transaction.setApprovedAmt(generateApprovedAmt());


            transactions.add(transaction);
        }

        Collections.sort(transactions, new Comparator<CardTransaction>() {
            @Override
            public int compare(CardTransaction transaction1, CardTransaction transaction2) {
                Timestamp dtime1 = transaction1.getDtime();
                Timestamp dtime2 = transaction2.getDtime();
                return dtime1.compareTo(dtime2);
            }
        });

        return transactions;
    }

    private Long generateApprovedNum() {
        // 승인번호를 랜덤하게 생성 ( 6자리 랜덤 숫자 생성)
        return (long) ThreadLocalRandom.current().nextInt(100000, 1000000);
    }


    private String generateStatus() {
        // 결제 상태 코드 가중치 부여하여 랜덤으로 생성
        String[] statusCodes = {"01", "02", "03", "04"};
        double[] weights = {0.8, 0.1, 0.05, 0.05};

        double randomNumber = ThreadLocalRandom.current().nextDouble();
        double cumlativeProbability = 0.0;

        for (int i = 0; i < statusCodes.length; i++) {
            cumlativeProbability += weights[i];
            if (randomNumber <= cumlativeProbability) {
                return statusCodes[i];
            }
        }
        return statusCodes[0];

    }


    private Timestamp generateApprovedDtime() {
        Instant currentInstant = Instant.now();
        long randomDays = ThreadLocalRandom.current().nextLong(1, 366);
        Instant generatedInstant = currentInstant.minus(Duration.ofDays(randomDays));
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant seoulInstant = generatedInstant.atZone(seoulZoneId).toInstant();

        return Timestamp.from(seoulInstant);

    }



    private Timestamp generateTransDtime(Timestamp approvedDtime) {
        Instant approvedInstant = approvedDtime.toInstant();

        long randomDays = ThreadLocalRandom.current().nextLong(1, 31);
        Instant generatedInstant = approvedInstant.plus(Duration.ofDays(randomDays));

        return Timestamp.from(generatedInstant);
    }


    private BigDecimal generateApprovedAmt() {
        // 이용금액을 랜덤하게 생성
        int randomAmt = ThreadLocalRandom.current().nextInt(1000, 500001);
        return new BigDecimal(randomAmt - (randomAmt % 100));
    }

    private BigDecimal generateModifiedAmt() {
        // 정정 후 금액을 랜덤하게 생성
        int randomAmt = ThreadLocalRandom.current().nextInt(1000, 500001);
        return new BigDecimal(randomAmt - (randomAmt % 100));
    }

    private Integer generateTotalInstallCnt() {
        // 전체 할부 회차를 랜덤하게 생성 (예시로 1부터 12까지의 랜덤한 회차 생성)
        return ThreadLocalRandom.current().nextInt(1, 12);
    }

    public void delete() {
        cardTransactionRepository.deleteAll();
    }
}
