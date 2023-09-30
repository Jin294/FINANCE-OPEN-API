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
    private String firmName;

    private String firmCode;

    @Override
    public String toString() {
        return "Firm{" +
                "firmName='" + firmName + '\'' +
                ", firmCode='" + firmCode + '\'' +
                '}';
    }
}
