package com.example.demo.core.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 여부에 대한 열거형 상수
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum YesOrNo {
    Yes("Y", "여"),
    No("N", "부");

    private String code;
    private String title;

    /**
     * 여부에 해당되는 열거형 상수를 반환한다.
     *
     * @param code 코드
     *
     * @return 코드에 해당되는 열거형 상수
     */
    public static YesOrNo codeOf(boolean code) {
        return (code ? YesOrNo.Yes : YesOrNo.No);
    }

    /**
     * 코드에 해당되는 열거형 상수를 반환한다.
     *
     * @param code 코드
     *
     * @return 코드에 해당되는 열거형 상수
     */
    public static YesOrNo codeOf(String code) {
        for (YesOrNo em : values()) {
            if (em.getCode().equals(code)) {
                return em;
            }
        }

        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.getCode();
    }
}
