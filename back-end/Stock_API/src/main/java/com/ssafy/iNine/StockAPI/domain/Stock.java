package com.ssafy.iNine.StockAPI.domain;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String stockCode; // 종목코드(주식코드)

    private String accountNumber; // 계좌번호

    private int amount; // 수량

    private double cost; // 평단가
}
