package com.example.demo.core.util;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 일반적인 편의 기능을 제공하는 유틸리티이다.
 */
public class CommonUtil {
    /**
     * 접속 IP를 구한다.
     * @param request 서블릿 요청
     * @return Ip
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (Strings.isNullOrEmpty(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (Strings.isNullOrEmpty(ip)) {
            // 웹로직
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (Strings.isNullOrEmpty(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (Strings.isNullOrEmpty(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (Strings.isNullOrEmpty(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 전화번호를 마스킹한다.
     *
     * @param str 문자
     *
     * @return 마스킹 문자
     */
    public static String getMaskPhoneNum(String str) {
        String replaceString = str;

        Matcher matcher = Pattern.compile("^(\\d{3})-?(\\d{3,4})-?(\\d{4})$").matcher(str);

        if (matcher.matches()) {
            replaceString = "";

            boolean isHyphen = false;
            if (str.indexOf("-") > -1) {
                isHyphen = true;
            }

            for (int i = 1; i <= matcher.groupCount(); i++) {
                String replaceTarget = matcher.group(i);
                if (i == 2) {
                    char[] c = new char[replaceTarget.length()];
                    Arrays.fill(c, '*');

                    replaceString = replaceString + String.valueOf(c);
                }
                else {
                    replaceString = replaceString + replaceTarget;
                }

                if (isHyphen && i < matcher.groupCount()) {
                    replaceString = replaceString + "-";
                }
            }
        }

        return replaceString;
    }

    /**
     * 이름을 마스킹한다.
     *
     * @param str 문자
     *
     * @return 마스킹 문자
     */
    public static String getMaskName(String str) {
        String replaceString = str;

        String pattern = "";
        if (str.length() == 2) {
            pattern = "^(.)(.+)$";
        }
        else {
            pattern = "^(.)(.+)(.)$";
        }

        Matcher matcher = Pattern.compile(pattern).matcher(str);

        if (matcher.matches()) {
            replaceString = "";

            for (int i = 1; i <= matcher.groupCount(); i++) {
                String replaceTarget = matcher.group(i);
                if (i == 2) {
                    char[] c = new char[replaceTarget.length()];
                    Arrays.fill(c, '*');

                    replaceString = replaceString + String.valueOf(c);
                }
                else {
                    replaceString = replaceString + replaceTarget;
                }

            }
        }

        return replaceString;
    }

    /**
     * 이메일주소를 마스킹한다.
     *
     * @param str
     *
     * @return
     */
    public static String getMaskEmail(String str) {
        String replaceString = str;

        Matcher matcher = Pattern.compile("^(..)(.*)([@]{1})(.*)$").matcher(str);

        if (matcher.matches()) {
            replaceString = "";

            for (int i = 1; i <= matcher.groupCount(); i++) {
                String replaceTarget = matcher.group(i);
                if (i == 2) {
                    char[] c = new char[replaceTarget.length()];
                    Arrays.fill(c, '*');

                    replaceString = replaceString + String.valueOf(c);
                }
                else {
                    replaceString = replaceString + replaceTarget;
                }
            }

        }

        return replaceString;
    }

}
