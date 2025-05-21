package com.nts.ntsboard.config;

import com.nts.ntsboard.common.auth.AuthorizationFilter;
import com.nts.ntsboard.common.auth.JwtUtil;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final JwtUtil jwtUtil;

    public FilterConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public FilterRegistrationBean<Filter> authorizationFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(jwtUtil));
        registrationBean.addUrlPatterns("/*"); // 적용할 URL 패턴
        registrationBean.setOrder(1); // 필터 순서 (낮을수록 먼저 실행됨)
        return registrationBean;
    }
}