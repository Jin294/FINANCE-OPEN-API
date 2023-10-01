package com.ssafy.iNine.Document.domain.api.controller;

import com.ssafy.iNine.Document.common.response.DataResponse;
import com.ssafy.iNine.Document.domain.api.dto.ApiCategoryDto;
import com.ssafy.iNine.Document.domain.api.dto.ApiDocsDto;
import com.ssafy.iNine.Document.domain.api.service.ApiService;
import com.ssafy.iNine.Document.domain.api.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docs/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ApiController {
    private final ApiService apiService;
    private final CategoryService categoryService;
    @GetMapping
    public DataResponse<List<ApiDocsDto.Info>> getApiList() {
        List<ApiDocsDto.Info> data = apiService.getApiList();
        return new DataResponse(200, "success", data);
    }

    @GetMapping("/{api_pk}")
    public DataResponse<ApiDocsDto.DetailInfo> getApiDetail(@PathVariable("api_pk") Long apiPk) {
        ApiDocsDto.DetailInfo data = apiService.getDetailInfo(apiPk);
        return new DataResponse(200, "success", data);
    }

    @GetMapping("/category")
    public DataResponse<ApiCategoryDto.CategoryInfo> getCategoryList() {
        List<ApiCategoryDto.CategoryInfo> data = categoryService.getCategoryList();
        return new DataResponse(200, "success", data);
    }
}
