package com.iNine.resource.common.config;

import com.iNine.resource.common.filter.ApiTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.Filter;

@Configuration
public class WebConfig {
//    @Bean
//    public FilterRegistrationBean logFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new
//                FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new ApiTokenFilter());
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.addUrlPatterns("/api/exchange/**");
//        return filterRegistrationBean;
//    }
}
