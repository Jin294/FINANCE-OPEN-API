package com.ssafy.iNine.Document.common.entity.api;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="api_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_category_id")
    private Long ApiCategoryId;
    @Column(length = 256)
    private String title;
    @Column(length = 1000)
    private String detail;
}
