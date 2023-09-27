package com.ssafy.iNine.FinancialAPI.comsumption.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionDto {
    private String merchantType;
    private BigDecimal price;
    private BigDecimal percent;
}
