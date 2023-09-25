package com.ssafy.iNine.Document.domain.api.dto;

import com.ssafy.iNine.Document.common.entity.api.Api;
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
        private Long apiDocsId;
        private String title;
        private String content;

        public static Info of(Api api) {
            return Info.builder()
                    .apiDocsId(api.getApiDocsId())
                    .title(api.getTitle())
                    .content(api.getContent())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailInfo {
        private Long apiDocsId;
        private String title;
        private String content;
        private String method;
        private String return_type;
        private String content_type;
        private String authorization;
        List<ApiData> apiData;

        public static DetailInfo of(Api api, List<ApiData> apiData) {
            return DetailInfo.builder()
                    .apiDocsId(api.getApiDocsId())
                    .title(api.getTitle())
                    .authorization(api.getAuthorization())
                    .content(api.getContent())
                    .content_type(api.getContentType())
                    .method(api.getMethod())
                    .return_type(api.getReturnType())
                    .apiData(apiData)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApiData {
        private Integer request_id;
        private String title;
        private String type;
        private String detail;
        private Boolean is_essential;
        private Boolean is_Request;
    }
}
