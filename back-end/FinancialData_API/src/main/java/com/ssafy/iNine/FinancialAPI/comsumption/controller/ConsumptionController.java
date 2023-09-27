package com.ssafy.iNine.FinancialAPI.comsumption.controller;

import com.ssafy.iNine.FinancialAPI.common.response.DataResponse;
import com.ssafy.iNine.FinancialAPI.comsumption.dto.ConsumptionDto;
import com.ssafy.iNine.FinancialAPI.comsumption.service.ConsumptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConsumptionController {
    private final ConsumptionService consumptionService;

    @GetMapping("/consumption")
    public DataResponse<List<ConsumptionDto>> analysisConsumption(@RequestParam("userId") String userId) {
        List<ConsumptionDto> consumptionList = consumptionService.analysisConsumption(userId);
        return new DataResponse<>(200, "소비 내역 조회 성공", consumptionList);
    }

}
