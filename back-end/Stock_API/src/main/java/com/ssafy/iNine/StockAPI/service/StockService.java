package com.ssafy.iNine.StockAPI.service;

import com.ssafy.iNine.StockAPI.domain.StockAccount;
import com.ssafy.iNine.StockAPI.domain.StockFirm;
import com.ssafy.iNine.StockAPI.domain.TransactionRecord;
import com.ssafy.iNine.StockAPI.dto.StockAccountDto;
import com.ssafy.iNine.StockAPI.dto.StockDto;
import com.ssafy.iNine.StockAPI.dto.StockFirmDto;
import com.ssafy.iNine.StockAPI.repository.StockAccountRepository;
import com.ssafy.iNine.StockAPI.repository.StockFirmRepository;
import com.ssafy.iNine.StockAPI.repository.TransactionRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockFirmRepository firmRepository;
    private final StockAccountRepository accountRepository;
    private final TransactionRecordRepository recordRepository;

    /**
     * 고객번호와 증권사가 일치하는 증권계좌를 가져온다
     * (하나의 증권사에 여러개의 계좌를 가질수도 있으니까)
     *
     * @param userIdx
     * @param firmCode
     * @return
     */
    public List<StockAccountDto> getAccounts(int userIdx, String firmCode) {
        List<StockAccountDto> dtos = accountRepository.findByUserIdxAndFirmCode(userIdx, firmCode).stream()
                .map(stockAccount -> {
                    StockAccountDto dto = new StockAccountDto();
                    dto.setAccountNumber(stockAccount.getAccountNumber());
                    dto.setIsConsent(stockAccount.getIsConsent());
                    dto.setAccountName(stockAccount.getAccountName());
                    dto.setAccountType(stockAccount.getAccountType());
                    dto.setIssueDate(stockAccount.getIssueDate());
                    dto.setTaxBenefits(stockAccount.isTaxBenefits());

                    dto.setFirmCode(stockAccount.getFirmCode());
                    dto.setUserIdx(stockAccount.getUserIdx());

                    return dto;
                })
                .collect(Collectors.toList());

        return dtos;
    }

    /**
     * 모든 증권사의 고유코드를 가져온다
     *
     * @return 리스트에 담긴 증권사의 고유 코드목록
     */
    public List<StockFirmDto> getFirmCodes() {
        List<String> list = firmRepository.findAllFirmCode();
        List<StockFirmDto> firmcodes = new ArrayList<>();
        for (String code : list) {
            StockFirmDto tmp = new StockFirmDto();
            tmp.setFirmCode(code);
            firmcodes.add(tmp);
        }
        return firmcodes;
    }

    /**
     * 해당 계좌가 생성된 날짜를 리턴한다
     *
     * @param accountNumber
     * @return
     */
    public LocalDateTime getCreatedAt(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).getIssueDate();
    }

    /**
     * 해당 계좌가 최종적으로 업데이트된 날짜를 리턴한다
     *
     * @param accountNumber
     * @return
     */
//    public LocalDateTime getUpdatedAt(String accountNumber) {
//        return accountRepository.findByAccountNumber(accountNumber).getUpdatedAt();
//    }

    /**
     * 거래기록으로부터 내 주식보유내역 구하기
     * 1. 계좌 개설날짜 구하기
     * 2. 거래내역에서 내 계좌번호와 일치하고 거래가 체결된 내역들을 불러오기
     * 3. 2에서 불러온 내역들을 주식종목 별로 분류하기
     * 4. 체결 여부 확인하면서 수량 및 평단가 계산
     *
     * @param accountNumber 계좌번호
     * @return
     */
    public Map<String, StockDto> getStocksFromRecords(String accountNumber) {
        // 1.
        LocalDateTime createdAt = getCreatedAt(accountNumber);
        // 2. 계좌번호가 일치하고, 체결된 거래내역 불러오기
        List<TransactionRecord> records = recordRepository.findByAccountNumberAndIsFinal(accountNumber, 1);
        // 3. 종목 별로 분류
        Map<String, List<TransactionRecord>> recordsByCode = new HashMap<>();
        for (TransactionRecord record : records) {
            if (!recordsByCode.containsKey(record.getStockCode())) {
                recordsByCode.put(record.getStockCode(), new ArrayList<>());
            }
            recordsByCode.get(record.getStockCode()).add(record);
        }

        Map<String, StockDto> map = new HashMap<>();
        // 4.
        for (String key : recordsByCode.keySet()) {
            int count = 0;
            double cost = 0;
            // 한 종목의 체결목록
            List<TransactionRecord> list = recordsByCode.get(key);
            for (TransactionRecord record : list) {
                if (record.getStatus() == 0) {
                    // 매수
                    count += record.getAmount();
                    // 평단가 업데이트 : (기존수량 * 기존평단가 + 추가된수량 * 추가된평단가) / (전체 수량)
                    cost = (record.getCost() * record.getAmount() + cost * count) / (record.getAmount() + count);
                } else { // 매도
                    // 매도 시에는 평단가 업데이트 없음
                    count -= record.getAmount();
                }
            }

            map.put(key, new StockDto().builder().amount(count).cost(cost).build());
        }
        return map;
    }
}
