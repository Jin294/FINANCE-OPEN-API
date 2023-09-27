package com.ssafy.iNine.FinancialAPI.comsumption.service;

import com.ssafy.iNine.FinancialAPI.card.repository.CardRepository;
import com.ssafy.iNine.FinancialAPI.cardtransaction.repository.CardTransactionRepository;
import com.ssafy.iNine.FinancialAPI.cardtransaction.repository.MerchantRepository;
import com.ssafy.iNine.FinancialAPI.common.exception.CommonException;
import com.ssafy.iNine.FinancialAPI.common.exception.ExceptionType;
import com.ssafy.iNine.FinancialAPI.comsumption.dto.ConsumptionDto;
import com.ssafy.iNine.FinancialAPI.entity.Card;
import com.ssafy.iNine.FinancialAPI.entity.CardTransaction;
import com.ssafy.iNine.FinancialAPI.entity.Merchant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumptionService {
    private final CardRepository cardRepository;
    private final CardTransactionRepository cardTransactionRepository;
    private final MerchantRepository merchantRepository;
    public List<ConsumptionDto> analysisConsumption(String userId) {
        List<ConsumptionDto> consumptionList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        List<Card> cardList = cardRepository.findByUserId(userId);
        List<CardTransaction> cardTransactionList = new ArrayList<>();
        for(Card card: cardList) {
            cardTransactionList.addAll(
                    cardTransactionRepository.
                            findAllByCardIdAndDtimeBetweenOrderByDtimeAsc(
                            card.getCardId(), new Timestamp(calendar.getTimeInMillis()),
                            new Timestamp(System.currentTimeMillis())));
        }

        Map<String, BigDecimal> prices = new HashMap<>(); // 업종별 총 소비 가격
        Map<Long, String> types = new HashMap<>(); // merchantId의 타입 이름
        BigDecimal total = new BigDecimal(0.0);
        for(CardTransaction transaction: cardTransactionList) {
            // 상점 타입 map 확인
            Long merchantId = transaction.getMerchantId();
            if(!types.containsKey(merchantId)) {
                Merchant merchant = merchantRepository.findById(merchantId)
                        .orElseThrow(() -> new CommonException(ExceptionType.MERCHANT_NOT_FOUND));
                types.put(merchantId, merchant.getMerchantType());
            }
            String merchantType = types.get(merchantId);

            BigDecimal price;
            if(transaction.getStatus().equals("02")) { // 취소
                price = new BigDecimal(0.0).subtract(transaction.getApprovedAmt());
            } else if(transaction.getStatus().equals("03")) { // 수정
                price = transaction.getModifiedAmt().subtract(transaction.getApprovedAmt());
            } else {
                price = transaction.getApprovedAmt();
            }
            total = total.add(price);
            prices.put(merchantType, prices.getOrDefault(merchantType, new BigDecimal(0.0)).add(price));
        }

        final BigDecimal totalPrice = new BigDecimal(total.doubleValue());
        prices.forEach((key, value)->{
            ConsumptionDto consumptionDto = new ConsumptionDto();
            consumptionDto.setMerchantType(key);
            consumptionDto.setPrice(value);
            if(totalPrice.compareTo(BigDecimal.ZERO) != 0) consumptionDto.setPercent(value.divide(totalPrice, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100.0)));
            consumptionList.add(consumptionDto);
        });

        return consumptionList;
    }
}
