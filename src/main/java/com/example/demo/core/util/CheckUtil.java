package com.example.demo.core.util;

/**
 * 검사 관련한 기능을 제공하는 유틸리티이다.
 *
 * @author jonghyeon
 */
public class CheckUtil {
    private CheckUtil() {
    }

    /**
     * 값이 NULL 이거나 공백인지 여부를 반환한다.
     *
     * @param value 값
     * @return 값이 NULL 이거나 공백인지 여부
     */
    public static boolean isNullOrEmpty(String value) {
        return (null == value || value.trim().isEmpty());
    }

    /**
     * 값이 NULL 이거나 공백이 아닌지 여부를 반환한다.
     *
     * @param value 값
     * @return 값이 NULL 또는 공백이 아닌지 여부
     */
    public static boolean isNotNullOrNotEmpty(String value) {
        return !isNullOrEmpty(value);
    }

    /**
     * 주어진 값이 NULL 이거나 공백이면 기본값을 반환한다.
     *
     * @param src        주어진값
     * @param defaultSrc 기본값
     * @return 주어진 값이 NULL 이거나 공백이면 기본값
     */
    public static String nvl(String src, String defaultSrc) {
        return (isNullOrEmpty(src) ? defaultSrc : src);
    }
}
