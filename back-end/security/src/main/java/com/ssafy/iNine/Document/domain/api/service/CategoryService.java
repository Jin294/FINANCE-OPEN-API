package com.ssafy.iNine.Document.domain.api.service;

import com.ssafy.iNine.Document.common.entity.api.Category;
import com.ssafy.iNine.Document.domain.api.dto.ApiCategoryDto;
import com.ssafy.iNine.Document.domain.api.repository.ApiCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final ApiCategoryRepository apiCategoryRepository;

    public List<ApiCategoryDto.CategoryInfo> getCategoryList() {
        List<Category> categoryList = apiCategoryRepository.findAll();
        List<ApiCategoryDto.CategoryInfo> res = new ArrayList<>();

        categoryList.forEach((category -> {
            res.add(ApiCategoryDto.CategoryInfo.of(category));
        }));
        return res;
    }
}
