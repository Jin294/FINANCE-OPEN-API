package com.ssafy.iNine.Document.domain.api.dto;

import com.ssafy.iNine.Document.common.entity.api.Api;
import com.ssafy.iNine.Document.common.entity.api.Category;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class ApiDocsDto {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private Long api_docs_id;
        private String title;
        private String content;
        private Category category;

        public static Info of(Api api) {
            return Info.builder()
                    .api_docs_id(api.getApiDocsId())
                    .title(api.getTitle())
                    .content(api.getContent())
                    .category(api.getCategory())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailInfo {
        private Long api_docs_id;
        private String title;
        private String content;
        private String method;
        private String return_type;
        private String content_type;
        private String authorization;
        private String endpoint;
        private String return_example;
        List<ApiData> api_data;

        public static DetailInfo of(Api api, List<ApiData> apiData) {
            return DetailInfo.builder()
                    .api_docs_id(api.getApiDocsId())
                    .title(api.getTitle())
                    .authorization(api.getAuthorization())
                    .content(api.getContent())
                    .content_type(api.getContentType())
                    .method(api.getMethod())
                    .return_type(api.getReturnType())
                    .api_data(apiData)
                    .endpoint(api.getEndpoint())
                    .return_example(api.getReturnExample())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiData {
        private String title;
        private String type;
        private String detail;
        private Boolean is_essential;
        private Boolean is_request;
        private Boolean is_parameter;
    }
}
