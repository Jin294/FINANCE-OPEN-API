package com.ssafy.iNine.StockAPI.service;

import com.ssafy.iNine.StockAPI.domain.Account;
import com.ssafy.iNine.StockAPI.domain.Product;
import com.ssafy.iNine.StockAPI.domain.TransactionRecord;
import com.ssafy.iNine.StockAPI.domain.User;
import com.ssafy.iNine.StockAPI.dto.AccountDto;
import com.ssafy.iNine.StockAPI.dto.ProductDto;
import com.ssafy.iNine.StockAPI.dto.FirmDto;
import com.ssafy.iNine.StockAPI.dto.TransactionRecordDto;
import com.ssafy.iNine.StockAPI.repository.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.*;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service {

    private final FirmRepository firmRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final TransactionRecordRepository recordRepository;
    private final UserRepository userRepository;

    private static final int ACCOUNT_NUMBER_LENGTH = 25; // 10 기관코드 + 12 숫자 + 3 구분자('-') = 총 25 글자
    private static final String SEPARATOR = "-";

    private static final Random random = new Random();

    /**
     * 고객번호와 단일 증권사가 일치하는 복수의 증권계좌를 가져온다
     * (하나의 증권사에 여러개의 계좌를 가질수도 있으니까)
     *
     * @param userId 유저의 아이디(이메일)
     * @param orgCode 기관(증권사)코드 리스트
     * @return 계좌목록
     */
    public List<AccountDto> getAccountsFromSingleFirm(String userId, String orgCode) {
        if (!userRepository.existsByUserId(userId)) {
            makeAccount(userId);
        }
        return accountRepository.findByUserIdAndFirmCode(userId, orgCode).stream()
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
    public List<FirmDto> getFirmObjects() {
        return firmRepository.findAll().stream().map(obj -> FirmDto.builder()
                    .firmName(obj.getFirmName())
                    .firmCode(obj.getFirmCode())
                    .build()).collect(Collectors.toList());
    }

    /**
     * 증권사별로 내 계좌가 존재하는지 조회한다.
     * @param userId 유저의 아이디(이메일)
     * @param orgCodes 기관(증권사)코드 리스트
     * @return 증권사별 계좌목록
     */
    public Map<String, List<AccountDto>> getAccountsFromAllFirms(String userId, List<FirmDto> orgCodes) {
        if(!userRepository.existsByUserId(userId)) {
            userRepository.save(new User(userId));
            makeAccount(userId);
        }
        Map<String, List<AccountDto>> result = new HashMap<>();

        // 모든 증권사의 코드
        for (FirmDto dto : orgCodes) {
            // 단일 증권사의 계좌목록을 가져온다.
            List<AccountDto> list = getAccountsFromSingleFirm(userId, dto.getFirmCode());
            // 해당 증권사에 내 계좌가 존재한다면 취합한다.
            if (list != null && list.size() != 0) {
                result.put(dto.getFirmName(), list);
            }
        }

        return result;
    }


    /**
     * fromDate와 toDate 사이의 거래내역 구하기
     * @param orgCode 기관(증권사)코드
     * @param accountNum 계좌번호
     * @param fromDate 검색시작일자
     * @param toDate 검색끝일자
     * @return 거래내역리스트
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
        // 개설 날짜
        LocalDateTime createdAt = accountRepository.findByAccountNumber(accountNum).getIssueDate();

        // 나의 거래내역 구하기
        List<TransactionRecord> recordsFromCreated = recordRepository.findByAccountNumberAndTransDtimeBetween(accountNum, createdAt, LocalDateTime.now());

        Map<String, ProductDto> productDtoMap = new HashMap<>();

        // 거래 내역을 순회하면서 ProductDto를 생성하고 맵에 저장
        for (TransactionRecord record : recordsFromCreated) {
            String prodCode = record.getProdCode();
            Product target = productRepository.findByProdCode(prodCode);
            ProductDto productDto = ProductDto.builder()
                    .prodType(target.getProdType())
                    .prodCode(record.getProdCode())
                    .exCode(record.getExCode())
                    .prodName(record.getProdName())
                    .posType(record.getTransTypeDetail()) // 예시로 상세 거래 종류를 파생상품 포지션구분으로 설정
                    .creditType("01") // 예시로 현금으로 설정
                    .purchaseAmt(record.getTransAmt()) // 거래금액을 매입금액으로 설정
                    .holdingNum(record.getTransNum()) // 거래수량을 보유수량으로 설정
                    .evalAmt(record.getTransNum() * record.getBaseAmt()) // 평가금액을 계산
                    .currencyCode(record.getCurrencyCode())
                    .build();
            if (!productDtoMap.containsKey(prodCode)) {
                // 새로운 상품 코드인 경우, ProductDto 생성 및 맵에 추가
                productDtoMap.put(prodCode, productDto);
            } else {
                // 이미 해당 상품 코드에 대한 ProductDto가 존재하는 경우, 해당 상품 정보 업데이트
                ProductDto existingProductDto = productDtoMap.get(prodCode);
                if (record.getTransType().equals("305")) { // 매수
                    existingProductDto.setPurchaseAmt(existingProductDto.getPurchaseAmt() + record.getTransAmt());
                    existingProductDto.setHoldingNum(existingProductDto.getHoldingNum() + record.getTransNum());
                    existingProductDto.setEvalAmt(existingProductDto.getHoldingNum() * existingProductDto.getPurchaseAmt());
                } else if (record.getTransType().equals("306")) { // 매도
                    existingProductDto.setPurchaseAmt(existingProductDto.getPurchaseAmt() - record.getTransAmt());
                    existingProductDto.setHoldingNum(existingProductDto.getHoldingNum() - record.getTransNum());
                    existingProductDto.setEvalAmt(existingProductDto.getHoldingNum() * existingProductDto.getPurchaseAmt());
                }
            }
        }

        // Map에 저장된 ProductDto 객체들을 List로 변환하여 반환
        return new ArrayList<>(productDtoMap.values());
    }

    // 신규 계좌 생성
    public void makeAccount(String userId){
        List<String> firmCode = firmRepository.findAllFirmCode();
        int len = firmCode.size();
        int limit = (int) (random() * len);
        // 랜덤한 개수의 계좌 생성
        for (int i = 0; i < limit; i++) {
            String orgCode = firmCode.get((int) (random() * len));
            Account accountTemplate = Account.builder()
                    .userId(userId)
                    .firmCode(orgCode)
                    .accountNumber(generateAccountNumber(orgCode))
                    .isConsent(false)
                    .accountType("101")
                    .issueDate(LocalDateTime.now())
                    .isTaxBenefits(false)
                    .build();

            accountTemplate.setAccountName(firmRepository.getFirmName(accountTemplate.getFirmCode()) + (int)(random() * 100 + 1));
            accountRepository.save(accountTemplate);
            makeRecords(userId, accountTemplate.getFirmCode(), accountTemplate.getAccountNumber());
        }
    }

    // 신규 거래내역을 100개 생성
    public void makeRecords(String userId, String firmCode, String accountNumber){
        Account account = accountRepository.findByAccountNumber(accountNumber);
        List<Product> productList = productRepository.findAll();
        int len = productList.size();

        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < 100; i++) {
            now = now.plusSeconds((long)(random() * 1800 + 1));

            if (now.getHour() < 9 || now.getHour() >= 16 ) {
                continue;
            }

            if (now.getHour() == 15 && now.getMinute() > 30) {
                continue;
            }

            Product product = productList.get((int) (random() * len));
            int transNum = random.nextInt(10) + 1;
            int baseAmt = (random.nextInt(100) + 1) * 1000;
            int total = transNum * baseAmt;
            double remain = account.getRemainAmt();
            if (remain - transNum * baseAmt < 0) {
                break;
            }

            TransactionRecord tmp = TransactionRecord.builder()
                    .userId(userId)
                    .orgCode(firmCode)
                    .accountNumber(accountNumber)

                    .prodName(product.getProdName())
                    .prodCode(product.getProdCode())
                    .transDtime(now)
                    .transNo(String.valueOf(now))
                    .transType("305")
                    .transTypeDetail("")
                    .transNum(transNum)
                    .baseAmt(baseAmt)
                    .transAmt(total)
                    .settleAmt(total)
                    .balanceAmt(account.getRemainAmt())
                    .build();

            recordRepository.save(tmp);
        }
    }

    public String generateAccountNumber(String orgCode) {
        StringBuilder accountNumber = new StringBuilder(orgCode);

        for (int i = 0; i < 12; i++) {
            if (i % 5 == 0) {
                accountNumber.append(SEPARATOR); // 매 4글자마다 구분자('-') 추가
            }
            int digit = random.nextInt(10);
            accountNumber.append(digit);
        }

        return accountNumber.toString();
    }
}
