package com.ssafy.iNine.StockAPI.controller;

import com.ssafy.iNine.StockAPI.dto.AccountDto;
import com.ssafy.iNine.StockAPI.dto.FirmDto;
import com.ssafy.iNine.StockAPI.dto.TransactionRecordDto;
import com.ssafy.iNine.StockAPI.service.Service;
import com.ssafy.iNine.StockAPI.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invest")
@RequiredArgsConstructor
public class Controller {

    private final Service service;

    /**
     * 마이데이터 표준 : /v1/invest/accounts
     * 특정 증권사의 계좌목록 조회
     * @return 해당 증권사에 존재하는 나의 계좌목록들
     */
    @GetMapping("/accounts/{orgCode}")
    public ResponseEntity<Map<String, Object>> getAccountsByFirm(int userIdx, @PathVariable String orgCode, @Nullable String searchTimestamp, @Nullable String nextPage, int limit) {
        Map<String, Object> map = new HashMap<>();
        List<AccountDto> accounts = service.getAccounts(userIdx, orgCode);

        // rsp_code : 세부 응답코드
        map.put("rsp_code", 0);
        map.put("rsp_msg", 0);
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
    @GetMapping("/accounts/list")
    public ResponseEntity<Map<String, Object>> getAllAccounts(int userIdx) {
        Map<String, Object> map = new HashMap<>();

        Map<String, List<AccountDto>> accountsByFirm = new HashMap<>();
        // 모든 증권사의 코드를 가져온다
        List<FirmDto> codes = service.getFirmCodes();
        for (FirmDto dto : codes) {
            // 증권사별로 내 계좌가 존재하는지 조회한다.
            List<AccountDto> list = service.getAccounts(userIdx, dto.getFirmCode());
            // 해당 증권사에 내 계좌가 존재한다면 취합한다.
            if (list != null && list.size() != 0) {
                accountsByFirm.put(dto.getFirmCode(), list);
            }
        }
        // myAccounts 내에 증권사 코드별로 계좌번호가 취합됨
        map.put("myAccounts", accountsByFirm);
        return ResponseEntity.ok(map);
    }

    /**
     * 마이데이터 표준 : /v1/invest/accounts/products
     * 정보주체가 보유한 계좌에 포함된 상품의 조회 시점 기준 상세 정보 조회
     * @param userIdx 고객 고유번호
     * @param orgCode 정보제공자 기관코드
     * @param accountNum 계좌번호
     * @param searchTimestamp 조회 타임스탬프
     * @param nextPage 다음페이지 기준개체
     * @param limit 최대조회갯수
     * @return
     */
    @GetMapping("/accounts/detail")
    public ResponseEntity<Map<String, Object>> getAccountDetail(int userIdx, @PathVariable String orgCode, String accountNum, @Nullable String searchTimestamp, @Nullable String nextPage, int limit) {
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
     * @param userIdx
     * @return
     */
    @GetMapping("/accounts/all")
    public ResponseEntity<Map<String, Object>> getAllOfMine(int userIdx) {
        Map<String, Object> map = new HashMap<>();
        Map<String, List<AccountDto>> accountsByFirm = new HashMap<>();

        // 모든 증권사의 코드를 가져온다
        List<FirmDto> codes = service.getFirmCodes();
        for (FirmDto dto : codes) {
            // 각 증권사 별 계좌목록 리스트
            List<AccountDto> list = service.getAccounts(userIdx, dto.getFirmCode());

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
     * @param orgCode
     * @param accountNum
     * @param fromDate
     * @param toDate
     * @param nextPage
     * @param limit
     * @return
     */
    public ResponseEntity<Map<String, Object>> getTransactions(String orgCode, String accountNum, LocalDateTime fromDate, LocalDateTime toDate, @Nullable String nextPage, int limit) {
        Map<String, Object> map = new HashMap<>();

        List<TransactionRecordDto> list = service.getRecords(orgCode, accountNum, fromDate, toDate);
        map.put("rsp_code", 0);
        map.put("rsp_msg", 0);
        map.put("trans_cnt", list.size());
        map.put("trans_list", list);

        return ResponseEntity.ok(map);
    }
}
