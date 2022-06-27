package com.example.demo.core.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 환경코드 열거형 상수이다.
 */
@Getter
@AllArgsConstructor
public enum EnvCd {
    None("none", "없음"),
    Local("local", "로컬"),
    Develop("dev", "개발"),
    QA("qa", "QA"),
    Production("prod", "운영");

    private String code;
    private String title;

    /**
     * 코드에 해당되는 열거형 상수를 반환한다.
     *
     * @param code 코드
     * @return 코드에 해당되는 열거형 상수
     */
    public static Optional<EnvCd> codeOf(String code) {
        return Arrays.stream(values()).filter(val -> val.getCode().equals(code)).findFirst();
    }

    @JsonValue
    @Override
    public String toString() {
        return this.getCode();
    }
}