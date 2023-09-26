package com.ssafy.iNine.FinancialAPI.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@ToString(of = {"cardId", "userId", "cardNum", "isConsent", "cardName", "cardMember", "cardType"})
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId; // 카드 식별자
    private Long userId; // 사용자 ID
    private String cardNum;     // 카드 번호
    private Boolean isConsent; // 전송요구 여부
    private String cardName; // 카드상품명
    private Integer cardMember; // 본인/가족 구분코드 1:본인, 2: 가족
    private String cardType; // 01: 신용,  02: 체크(직불포함), 03: 소액신용체크


}
