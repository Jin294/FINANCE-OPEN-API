package com.ssafy.iNine.FinancialAPI.card.service;

import com.ssafy.iNine.FinancialAPI.card.dto.CardDto;
import com.ssafy.iNine.FinancialAPI.card.repository.CardRepository;
import com.ssafy.iNine.FinancialAPI.common.exception.CommonException;
import com.ssafy.iNine.FinancialAPI.common.exception.ExceptionType;
import com.ssafy.iNine.FinancialAPI.entity.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;


    // userId로 카드 정보 저장된게 있는지 찾고 없으면 카드 더미 데이터를 생성하고 있으면 기존 카드 정보를 반환
    public CardDto.CardResponseDto getUserCardList(CardDto.CardRequestDto cardRequestDto) {

        CardDto.CardResponseDto cardResponse = new CardDto.CardResponseDto();

        // userId로 카드 정보 조회
        List<Card> cardList = cardRepository.findByUserId(cardRequestDto.getUserId());



        // 카드 정보가 없으면 더미 데이터 생성
        if (cardList == null || cardList.isEmpty()) {
            cardList = generateUserCards(cardRequestDto.getUserId());
            cardRepository.saveAll(cardList); // 생성된 더미 데이터를 저장
        }

        //페이지네이션
        String nextPage = cardRequestDto.getNextPage();
        Integer limit = cardRequestDto.getLimit();


        //페이지네이션 처리
        List<CardDto.CardDataDto> paginatedCardList = paginateCard(cardList, nextPage, limit);

        // paginatedCardList의 마지막 값의 cardId와 cardList 중 일치하는 다음 cardId를 가져와서 nextPage로 세팅
        CardDto.CardDataDto lastPaginatedCard = paginatedCardList.get(paginatedCardList.size() - 1);

        for (int i = 0; i< cardList.size(); i++) {

            if (lastPaginatedCard.getCardId().equals(cardList.get(i).getCardId())) {
                if (i + 1 < cardList.size()) {
                    cardResponse.setNextPage(String.valueOf(Math.toIntExact(cardList.get(i + 1).getCardId())));
                }
            }
        }

        // 응답 객체 생성

        cardResponse.setCardCnt(cardList.size());
        cardResponse.setCardList(paginatedCardList);

        return cardResponse;
    }
    private List<CardDto.CardDataDto> paginateCard(List<Card> cardList, String nextPage, Integer limit) {
        int startIndex = -1;

        if (nextPage != null && !nextPage.isEmpty()) {
            // 이전 페이지에서 마지막으로 조회한 카드의 인덱스를 찾음
            for (int i = 0; i < cardList.size(); i++) {
                if (nextPage.equals(String.valueOf(cardList.get(i).getCardId()))) {
                    startIndex = i;
                    break;
                }
            }

            if (startIndex == -1) {
                // 유효한 'nextPage'가 주어졌지만 해당 카드를 찾을 수 없는 경우, 빈 리스트 반환
                return new ArrayList<>();
            }
        } else {
            startIndex = 0;
        }

        int endIndex = Math.min(startIndex + limit, cardList.size());

        // 페이지네이션된 카드 데이터 추출
        List<Card> paginatedCards = cardList.subList(startIndex, endIndex);

        // 카드 데이터를 CardDataDto 형태로 변환하여 반환
        List<CardDto.CardDataDto> paginatedCardData = new ArrayList<>();
        for (Card card : paginatedCards) {
            paginatedCardData.add(CardDto.CardDataDto.of(card));
        }

        return paginatedCardData;
    }




    public List<Card> generateUserCards(String userId) {
        int cardCnt = ThreadLocalRandom.current().nextInt(1, 11);

        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < cardCnt; i++) {
            CardDto.CardDataDto cardData = generateRandomCard();
            Card card = new Card();
            card.setUserId(userId);
            card.setCardId(cardData.getCardId());
            card.setCardNum(cardData.getCardNum());
            card.setIsConsent(cardData.getIsConsent());
            card.setCardName(cardData.getCardName());
            card.setCardMember(cardData.getCardMember());
            card.setCardType(cardData.getCardType());


            cards.add(card);
        }
        return cards;
    }

    private CardDto.CardDataDto generateRandomCard() {

        String cardNum = generateRandomCardNum();
        String cardName = generateRandomCardName();
        Integer cardMember = generateRandomCardMember();
        String cardType = generateRandomCardType();
        Boolean isConsent = true;

        return CardDto.CardDataDto.builder()
                .cardNum(cardNum)
                .cardName(cardName)
                .cardMember(cardMember)
                .cardType(cardType)
                .isConsent(isConsent)
                .build();

    }

    private String generateRandomCardNum() {
        Random random = new Random();
        long cardNumber = ThreadLocalRandom.current().nextLong(1_0000_0000_0000_0000L, 10_0000_0000_0000_0000L);
        String maskedCardNumber = maskCardNumber(cardNumber);
        return maskedCardNumber;
    }

    private String maskCardNumber(long cardNumber) {
        String cardNumberStr = String.valueOf(cardNumber);
        return cardNumberStr.substring(0, 4) + "********" + cardNumberStr.substring(13);
    }

    private String generateRandomCardName() {
        String[] cardNames = {"롯데", "비씨", "신한", "삼성", "우리", "하나", "현대", "KB국민"};
        return cardNames[new Random().nextInt(cardNames.length)];
    }



    private Integer generateRandomCardMember() {
        return new Random().nextDouble() < 0.1 ? 2 : 1;
    }

    private String generateRandomCardType() {
        String[] cardTypes = {"01", "02", "03"};
        return cardTypes[new Random().nextInt(cardTypes.length)];
    }




}
