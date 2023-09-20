package com.ssafy.iNine.StockAPI.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirmDto {
    private String firmName;
    private String firmCode;
}
