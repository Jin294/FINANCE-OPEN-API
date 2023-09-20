package com.ssafy.iNine.StockAPI.controller;

import com.ssafy.iNine.StockAPI.dto.StockAccountDto;
import com.ssafy.iNine.StockAPI.dto.StockFirmDto;
import com.ssafy.iNine.StockAPI.service.StockService;
import com.ssafy.iNine.StockAPI.domain.StockAccount;
import com.ssafy.iNine.StockAPI.dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    /**
     * 마이데이터 표준 : /v1/invest/accounts
     * 특정 증권사의 계좌목록 조회
     * @return 해당 증권사에 존재하는 나의 계좌목록들
     */
    public ResponseEntity<Map<String, Object>> getAccountsByFirm(int userIdx, String orgCode, int limit) {
        Map<String, Object> map = new HashMap<>();
        List<StockAccountDto> accounts = stockService.getAccounts(userIdx, orgCode);
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
     * 우리 서비스 오리지널
     * 모든 증권사의 계좌목록을 한 번에 조회
     * @return 금융사에 존재하는 나의 계좌목록들
     */
    @GetMapping("/accounts/list")
    public ResponseEntity<Map<String, Object>> getAllAccounts(int userIdx) {
        Map<String, Object> map = new HashMap<>();

        Map<String, List<StockAccountDto>> accountsByFirm = new HashMap<>();
        // 모든 증권사의 코드를 가져온다
        List<StockFirmDto> codes = stockService.getFirmCodes();
        for (StockFirmDto dto : codes) {
            // 증권사별로 내 계좌가 존재하는지 조회한다.
            List<StockAccountDto> list = stockService.getAccounts(userIdx, dto.getFirmCode());
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
     * 증권계좌의 보유상품정보 조회
     * @param accountNumber 계좌번호
     * @return 계좌 내의 주식보유수량 및 평단가
     */
    @GetMapping("/accounts/detail")
    public ResponseEntity<Map<String, Object>> getAccountDetail(String accountNumber) {
        Map<String, Object> map = new HashMap<>();
        Map<String, StockDto> stocks = stockService.getStocksFromRecords(accountNumber);
        map.put("stocks", stocks);
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
        Map<String, List<StockAccountDto>> accountsByFirm = new HashMap<>();

        // 모든 증권사의 코드를 가져온다
        List<StockFirmDto> codes = stockService.getFirmCodes();
        for (StockFirmDto dto : codes) {
            // 각 증권사 별 계좌목록 리스트
            List<StockAccountDto> list = stockService.getAccounts(userIdx, dto.getFirmCode());

            // 해당 증권사에 내 계좌가 존재한다면 취합한다.
            if (list != null && list.size() != 0) {
                accountsByFirm.put(dto.getFirmCode(), list);
            }
        }

        map.put("list", accountsByFirm);
        return ResponseEntity.ok(map);
    }

    /**
     * 마이데이터 표준
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

        return ResponseEntity.ok(map);
    }
}
