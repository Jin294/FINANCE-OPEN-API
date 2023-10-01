package com.ssafy.iNine.Document.domain.api.dto;

import com.ssafy.iNine.Document.common.entity.api.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiCategoryDto {
    @Getter
    @Setter
    @Builder
    public static class CategoryInfo {
        private String title;
        private String detail;

        public static CategoryInfo of(Category category) {
            ApiCategoryDto.CategoryInfo categoryInfo = CategoryInfo.builder()
                    .title(category.getTitle())
                    .detail(category.getDetail())
                    .build();

            return categoryInfo;
        }
    }
}
