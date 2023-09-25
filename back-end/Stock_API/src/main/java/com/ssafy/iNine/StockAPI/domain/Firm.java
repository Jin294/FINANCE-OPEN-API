package com.ssafy.iNine.StockAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "invest_firm")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Firm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String firmName;

    private String firmCode;
}
