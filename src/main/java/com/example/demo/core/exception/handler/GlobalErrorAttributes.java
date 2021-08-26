package com.example.demo.core.exception.handler;

import com.example.demo.core.domain.Errors;
import com.example.demo.core.exception.BaseRuntimeException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 전역 오류 응답 설정
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        Throwable thrown = getError(webRequest);

        if (null == thrown) {
            errorAttributes.put("code", Errors.INTERNAL_SERVER_ERROR.getCode());
            errorAttributes.put("message", errorAttributes.get("error"));
        }

        // 발생된 예외객체를 구하여 의미있는 메세지를 재설정한다.
        if (thrown instanceof BaseRuntimeException) {
            BaseRuntimeException bre = (BaseRuntimeException)thrown;
            errorAttributes.put("status", bre.getErrorStatus());
            errorAttributes.put("code", bre.getErrorCode());
            errorAttributes.put("message", bre.getErrorMessage());
        }

        return errorAttributes;
    }
}
