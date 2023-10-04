package com.ssafy.iNine.StockAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirmDto {
    @JsonProperty("firm_name")
    private String firmName;
    @JsonProperty("firm_code")
    private String firmCode;
}
