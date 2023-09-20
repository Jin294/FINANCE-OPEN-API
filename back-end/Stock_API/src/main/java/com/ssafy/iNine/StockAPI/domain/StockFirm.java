package com.ssafy.iNine.StockAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "stock_firm")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class StockFirm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String firmName;

    private String firmCode;
}
