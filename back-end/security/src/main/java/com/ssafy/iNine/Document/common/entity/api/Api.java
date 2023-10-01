package com.ssafy.iNine.Document.common.entity.api;

import com.ssafy.iNine.Document.domain.api.dto.ApiDocsDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="api")
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_docs_id")
    private Long apiDocsId;

    private String title;
    private String content;
    private String method;
    @Column(name="return_type")
    private String returnType;
    @Column(name="content_type")
    private String contentType;
    private String authorization;
    @OneToMany(mappedBy = "api", fetch = FetchType.LAZY)
    private List<ApiData> apiData = new ArrayList<>();
    @JoinColumn(name = "api_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column(name = "return_example", columnDefinition = "TEXT")
    private String returnExample;
}
