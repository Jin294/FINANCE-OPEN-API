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
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    private final FirmRepository firmRepository;
    private final AccountRepository accountRepository;
    private final TransactionRecordRepository recordRepository;

    /**
     * 고객번호와 증권사가 일치하는 증권계좌를 가져온다
     * (하나의 증권사에 여러개의 계좌를 가질수도 있으니까)
     *
     * @param userIdx
     * @param firmCode
     * @return
     */
    public List<AccountDto> getAccounts(int userIdx, String firmCode) {
        List<AccountDto> dtos = accountRepository.findByUserIdxAndFirmCode(userIdx, firmCode).stream()
                .map(account -> {
                    AccountDto dto = new AccountDto();
                    dto.setAccountNumber(account.getAccountNumber());
                    dto.setIsConsent(account.getIsConsent());
                    dto.setAccountName(account.getAccountName());
                    dto.setAccountType(account.getAccountType());
                    dto.setIssueDate(account.getIssueDate());
                    dto.setTaxBenefits(account.isTaxBenefits());

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
     * @param accountNum 계좌번호
     * @return
     */
    public List<TransactionRecordDto> getRecords(String orgCode, String accountNum, LocalDateTime fromDate, LocalDateTime toDate) {
        return recordRepository.findByAccountNumberAndIdTradedAtBetween(accountNum, fromDate, toDate).stream().map(
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

    public List<ProductDto> getProductsFromRecords(String accountNum) {
        return null;
    }
}
