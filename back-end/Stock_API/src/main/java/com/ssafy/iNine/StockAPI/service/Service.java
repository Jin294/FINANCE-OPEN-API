package com.ssafy.iNine.StockAPI.service;

import com.ssafy.iNine.StockAPI.dto.AccountDto;
import com.ssafy.iNine.StockAPI.dto.ProductDto;
import com.ssafy.iNine.StockAPI.dto.FirmDto;
import com.ssafy.iNine.StockAPI.dto.TransactionRecordDto;
import com.ssafy.iNine.StockAPI.repository.AccountRepository;
import com.ssafy.iNine.StockAPI.repository.FirmRepository;
import com.ssafy.iNine.StockAPI.repository.TransactionRecordRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    private final FirmRepository firmRepository;
    private final AccountRepository accountRepository;
    private final TransactionRecordRepository recordRepository;

    /**
     * 고객번호와 단일 증권사가 일치하는 복수의 증권계좌를 가져온다
     * (하나의 증권사에 여러개의 계좌를 가질수도 있으니까)
     *
     * @param userId
     * @param firmCode
     * @return
     */
    public List<AccountDto> getAccountsFromSingleFirm(String userId, String firmCode) {

        return accountRepository.findByUserIdAndFirmCode(userId, firmCode).stream()
                .map(account -> {
                    AccountDto dto = new AccountDto();
                    dto.setAccountNumber(account.getAccountNumber());
                    dto.setConsent(account.isConsent());
                    dto.setAccountName(account.getAccountName());
                    dto.setAccountType(account.getAccountType());
                    dto.setIssueDate(account.getIssueDate());
                    dto.setTaxBenefits(account.isTaxBenefits());

                    return dto;
                })
                .collect(Collectors.toList());
    }

    /**
     * 모든 증권사의 고유코드를 가져온다
     *
     * @return 리스트에 담긴 증권사의 고유 코드목록
     */
    public List<FirmDto> getFirmCodes() {
        List<String> list = firmRepository.findAllFirmCode();
        List<FirmDto> firmcodes = new ArrayList<>();
        for (String code : list) {
            FirmDto tmp = new FirmDto();
            tmp.setFirmCode(code);
            firmcodes.add(tmp);
        }
        return firmcodes;
    }

    /**
     * 증권사별로 내 계좌가 존재하는지 조회한다.
     * @param userId
     * @param firmCodes
     * @return
     */
    public Map<String, List<AccountDto>> getAccountsFromAllFirms(String userId, List<FirmDto> firmCodes) {
        Map<String, List<AccountDto>> result = new HashMap<>();

        // 모든 증권사의 코드
        for (FirmDto dto : firmCodes) {
            // 단일 증권사의 계좌목록을 가져온다.
            List<AccountDto> list = getAccountsFromSingleFirm(userId, dto.getFirmCode());
            // 해당 증권사에 내 계좌가 존재한다면 취합한다.
            if (list != null || list.size() != 0) {
                result.put(dto.getFirmCode(), list);
            }
        }

        return result;
    }


    /**
     * fromDate와 toDate 사이의 거래내역 구하기
     * @param orgCode
     * @param accountNum
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<TransactionRecordDto> getRecords(String orgCode, String accountNum, LocalDateTime fromDate, LocalDateTime toDate) {
        return recordRepository.findByAccountNumberAndTransDtimeBetween(accountNum, fromDate, toDate).stream().map(
                record -> TransactionRecordDto.builder()
                            .prodName(record.getProdName())
                            .prodCode(record.getProdCode())
                            .transDtime(record.getTransDtime())
                            .transNo(record.getTransNo())
                            .transType(record.getTransType())
                            .transTypeDetail(record.getTransTypeDetail())
                            .transNum(record.getTransNum())
                            .baseAmt(record.getBaseAmt())
                            .transAmt(record.getTransAmt())
                            .settleAmt(record.getSettleAmt())
                            .balanceAmt(record.getBalanceAmt())
                            .currencyCode(record.getCurrencyCode())
                            .exCode(record.getExCode())
                            .build()).collect(Collectors.toList());
    }

    /**
     * 거래기록으로부터 내 주식보유내역 구하기
     * 1. 계좌 개설날짜 구하기
     * 2. 거래내역에서 내 계좌번호와 일치하고 거래가 체결된 내역들을 불러오기
     * 3. 2에서 불러온 내역들을 주식종목 별로 분류하기
     * 4. 체결 여부 확인하면서 수량 및 평단가 계산
     *
     * @param accountNum 계좌번호
     * @return
     */
    public List<ProductDto> getProductsFromRecords(String accountNum) {
        return null;
    }
}
