package com.example.demo.core.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.StandardCharsets;

/**
 * 중요 설정
 */
@Configuration
public class CoreConfig {
    /**
     * http 거래 캐릭터셋 UTF_8 설정
     *
     * @return http 거래 캐릭터셋 UTF_8 설정
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    /**
     * 설정 파일 비밀번호를 암복호화한다.
     *
     * @return 설정 파일 비밀번호 암복호화 객체
     */
    @Bean
    static public StandardPBEStringEncryptor standardPBEStringEncryptor() {
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
        config.setPassword("ikb-con-pwd");
        config.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setConfig(config);
        return pbeEnc;
    }
}
