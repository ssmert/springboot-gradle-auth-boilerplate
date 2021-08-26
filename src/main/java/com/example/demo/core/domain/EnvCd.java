package com.example.demo.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 환경코드 열거형 상수이다.
 */
@Getter
@AllArgsConstructor
public enum EnvCd {
    LOCAL("loc", "로컬"),
    DEVELOP("dev", "개발"),
    TEST("test", "테스트"),
    STAGING("stg", "스테이징"),
    PRODUCTION("prod", "운영");

    private String code;
    private String title;

    /**
     * 코드에 해당되는 열거형 상수를 반환한다.
     *
     * @param code 코드
     *
     * @return 코드에 해당되는 열거형 상수
     */
    public static EnvCd codeOf(String code) {
        for (EnvCd em : values()) {
            if (em.code.equals(code)) {
                return em;
            }
        }

        return EnvCd.LOCAL;
    }

    @Override
    public String toString() {
        return this.code;
    }
}