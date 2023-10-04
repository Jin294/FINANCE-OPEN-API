package com.ssafy.iNine.Document.domain.api.service;

import com.ssafy.iNine.Document.common.entity.api.Api;
import com.ssafy.iNine.Document.common.exception.CommonException;
import com.ssafy.iNine.Document.common.exception.ExceptionType;
import com.ssafy.iNine.Document.domain.api.dto.ApiDocsDto;
import com.ssafy.iNine.Document.domain.api.repository.ApiRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
@AllArgsConstructor
public class ApiService {
    private final ApiRepository apiRepository;

    public List<ApiDocsDto.Info> getApiList() {
        List<Api> apiList = apiRepository.findAll();
        List<ApiDocsDto.Info> infoList = new ArrayList<>();

        apiList.forEach(api -> {
            ApiDocsDto.Info info = ApiDocsDto.Info.of(api);
            infoList.add(info);
        });

        return infoList;
    }

    public ApiDocsDto.DetailInfo getDetailInfo(Long apiId) {
        Api api = apiRepository.getApiById(apiId).orElseThrow(()-> {
            return new CommonException(ExceptionType.API_NOT_FOUND);
        });
        List<ApiDocsDto.ApiData> apiDataList = new ArrayList<>();
        api.getApiData().forEach(apiData -> {
            ApiDocsDto.ApiData data = ApiDocsDto.ApiData.builder()
                    .title(apiData.getTitle())
                    .type(apiData.getType())
                    .detail(apiData.getDetail())
                    .is_essential(apiData.getIsEssential())
                    .is_request(apiData.getIsRequest())
                    .is_parameter(apiData.getIsParameter())
                    .build();

            apiDataList.add(data);
        });
        return ApiDocsDto.DetailInfo.of(api, apiDataList);
    }
}
