package com.ssafy.iNine.StockAPI.controller;

import com.ssafy.iNine.StockAPI.dto.*;
import com.ssafy.iNine.StockAPI.service.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invest")
@RequiredArgsConstructor
public class Controller {

    private final Service service;

    /**
     * 마이데이터 표준 : /v1/invest/accounts
     * 특정 증권사의 계좌목록 조회
     * @return 해당 증권사에 존재하는 나의 계좌목록들
     */
    @GetMapping("/accounts/{userId}")
    public ResponseEntity<Map<String, Object>> getAccountsByFirm(@PathVariable String userId, String orgCode, LocalDateTime searchTimeStamp, String nextPage, int limit) {
        if (searchTimeStamp == null) {
            searchTimeStamp = LocalDateTime.now();
        }
        System.out.println("userId = " + userId + ", orgCode = " + orgCode + ", searchTimeStamp = " + searchTimeStamp + ", nextPage = " + nextPage + ", limit = " + limit);
        Map<String, Object> map = new HashMap<>();
        List<AccountDto> accounts = service.getAccountsFromSingleFirm(userId, orgCode);
//        System.out.println("accounts : " + accounts.get(0));

        // rsp_code : 세부 응답코드
        map.put("rsp_code", 00000);
        map.put("rsp_msg", "성공");
        map.put("search_timestamp", 0);
        map.put("next_page", 0);
        map.put("account_cnt", accounts.size());
        map.put("account_list", accounts);

        return ResponseEntity.ok(map);
    }

    /**
     * 나의 오리지널
     * 모든 증권사의 계좌목록을 한 번에 조회
     * @return 금융사에 존재하는 나의 계좌목록들
     */
    @GetMapping("/myAllAccountnumbers/{userId}")
    public ResponseEntity<Map<String, Object>> getAllAccounts(@PathVariable String userId) {
        Map<String, Object> map = new HashMap<>();

        // myAccounts 내에 증권사 코드별로 계좌번호가 취합됨
        map.put("myAccounts", service.getAccountsFromAllFirms(userId, service.getFirmCodes()));
        return ResponseEntity.ok(map);
    }

    /**
     * 마이데이터 표준 : /v1/invest/accounts/products
     * 정보주체가 보유한 계좌에 포함된 상품의 조회 시점 기준 상세 정보 조회
     * @param userId 고객 고유번호
     * @param orgCode
     * @param accountNum
     * @param searchTimestamp
     * @param nextPage
     * @param limit
     * @return
     */
    @PostMapping("/accounts/detail")
    public ResponseEntity<Map<String, Object>> getAccountDetail(String userId, String orgCode, String accountNum, String searchTimestamp, String nextPage, String limit) {
        Map<String, Object> map = new HashMap<>();
        List<ProductDto> products = service.getProductsFromRecords(accountNum);

        map.put("rsp_code", 0);
        map.put("rsp_msg", 0);
        map.put("search_timestamp", 0);
        map.put("next_page", 0);
        map.put("base_date", 0);
        map.put("prod_cnt", products.size());
        map.put("prod_list", products);

        return ResponseEntity.ok(map);
    }

    /**
     * 우리 서비스 오리지널
     * 모든 증권사의 내 투자정보를 한 번에 조회
     * @param userId
     * @return
     */
    @PostMapping("/myAllInvest/{userId}")
    public ResponseEntity<Map<String, Object>> getAllOfMine(@PathVariable String userId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, List<AccountDto>> accountsByFirm = new HashMap<>();

        // 모든 증권사의 코드를 가져온다
        List<FirmDto> codes = service.getFirmCodes();
        for (FirmDto dto : codes) {
            // 각 증권사 별 계좌목록 리스트
            List<AccountDto> list = service.getAccountsFromSingleFirm(userId, dto.getFirmCode());

            // 해당 증권사에 내 계좌가 존재한다면 취합한다.
            if (list != null && list.size() != 0) {
                accountsByFirm.put(dto.getFirmCode(), list);
            }
        }

        map.put("list", accountsByFirm);
        return ResponseEntity.ok(map);
    }

    /**
     * 마이데이터 표준 : /v1/invest/accounts/transactions
     * fromDate와 toDate 사이의 거래내역 조회
     * @param userId
     * @param orgCode
     * @param accountNum
     * @param fromDate
     * @param toDate
     * @param nextPage
     * @param limit
     * @return
     */
    @PostMapping("/transRecord")
    public ResponseEntity<Map<String, Object>> getTransactions(String userId, String orgCode, String accountNum, LocalDateTime fromDate, LocalDateTime toDate, String nextPage, String limit) {
        Map<String, Object> map = new HashMap<>();

        List<TransactionRecordDto> list = service.getRecords(
                orgCode,
                accountNum,
                fromDate,
                toDate);

        map.put("rsp_code", 0);
        map.put("rsp_msg", 0);
        map.put("trans_cnt", list.size());
        map.put("trans_list", list);

        return ResponseEntity.ok(map);
    }
}
