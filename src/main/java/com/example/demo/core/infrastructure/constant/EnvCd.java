package com.example.demo.core.infrastructure.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 환경코드 열거형 상수이다.
 *
 * @author jonghyeon
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EnvCd {
    /**
     * 개발
     */
    Local("loc", "로컬"),

    /**
     * 개발
     */
    Develop("dev", "개발"),

    /**
     * 테스트
     */
    Test("test", "테스트"),

    /**
     * 스테이징
     */
    Staging("stg", "스테이징"),

    /**
     * 교육
     */
    Education("edu", "교육"),

    /**
     * 운영
     */
    Production("prod", "운영");

    /**
     * 코드
     */
    private String code;

    /**
     * 제목
     */
    private String title;

    /**
     * 코드에 해당되는 열거형 상수를 반환한다.
     *
     * @param code 코드
     *
     * @return 코드에 해당되는 열거형 상수
     */
    public final static EnvCd codeOf(String code) {
        for (EnvCd em : values()) {
            if (em.code.equals(code)) {
                return em;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.code;
    }
}