package com.example.demo.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 오류 정의
 */
@Getter
@AllArgsConstructor
public enum Errors {
    LOGIN_FAILED_ID_OR_PW(1001, "아이디 또는 비밀번호를 다시 확인해주세요."),
    SUSPEND_USER(1002, "정지된 사용자입니다.\n관리자에게 문의하세요."),
    INVALID_TOKEN(1003, "잘못된 토큰입니다."),
    EXPIRED_TOKEN(1004, "만료된 토큰입니다."),
    EXPIRED_ALL_TOKEN(1005, "세션이 만료되었습니다.\n다시 로그인 해주세요."),
    UNAUTHORIZED(1006, "인증되지 않은 사용자입니다."),
    BAD_REQUEST(1007, "잘못된 요청입니다."),


    NOT_FOUND_USER(2001, "존재하지 않는 사용자입니다."),
    NOT_FOUND_ROLE(2002, "존재하지 않는 역할입니다."),
    NOT_FOUND_MENU(2003, "존재하지 않는 메뉴입니다."),

    DUPLICATED_USER(3001, "동일한 사용자가 존재합니다."),
    DUPLICATED_ROLE(3002, "동일한 역할이 존재합니다."),

    INTERNAL_SERVER_ERROR(0, "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다.");

    private int code;
    private String message;

}
