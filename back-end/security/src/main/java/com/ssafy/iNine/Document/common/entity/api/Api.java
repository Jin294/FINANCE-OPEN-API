package com.ssafy.iNine.Document.common.entity.api;

import com.ssafy.iNine.Document.domain.api.dto.ApiDocsDto;
import lombok.*;

import javax.persistence.*;
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
    private Long apiDocsApi;

    private String title;
    private String content;
    private String method;
    @Column(name="return_type")
    private String returnType;
    @Column(name="content_type")
    private String contentType;
    private String authorization;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ApiData> apiData;
}
