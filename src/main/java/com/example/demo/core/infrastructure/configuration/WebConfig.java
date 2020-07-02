package com.example.demo.core.infrastructure.configuration;

import com.example.demo.core.infrastructure.constant.RequiredHeader;
import com.example.demo.core.infrastructure.converter.YesOrNoEnumConverter;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

/**
 * WebMvc를 설정한다.
 *
 * @author jonghyeon
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoggerInterceptor loggerInterceptor;

    public WebConfig(LoggerInterceptor loggerInterceptor) {
        this.loggerInterceptor = loggerInterceptor;
    }

    /**
     * 인터셉터 설정
     *
     * @param registry 레지스트리
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor).addPathPatterns("/**");
    }

    /**
     * 파라메터 사용자 정의 유형(enum등)에 대한 처리를 한다.
     *
     * @param registry 레지스트리
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new YesOrNoEnumConverter());
    }

    /**
     * CORS 설정
     *
     * @param registry 레지스트리
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 환경코드, 토큰 헤더설정
        String customHeaders = Joiner.on(", ").join(Iterators.transform(Lists.newArrayList(RequiredHeader.values()).iterator(), RequiredHeader::getCode));
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTION")
                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", customHeaders)
                .allowCredentials(false)
                .maxAge(3600);
    }
}
