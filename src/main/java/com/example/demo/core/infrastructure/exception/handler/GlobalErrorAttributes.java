package com.example.demo.core.infrastructure.exception.handler;


import com.example.demo.core.infrastructure.constant.Errors;
import com.example.demo.core.infrastructure.exception.BaseRuntimeException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 모든 예외에 대한 공통 기본 오류응답을 제공하는 클래스 이다.
 *
 * @author jonghyeon
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        Throwable thrown = super.getError(webRequest);

//        errorAttributes.put("code", "Unknown");
//        errorAttributes.put("message", null == thrown ? errorAttributes.get("message") : thrown.getMessage());

        //발생된 예외객체를 구하여 의미있는 메세지를 재설정한다.
        if (thrown instanceof BaseRuntimeException) {
            BaseRuntimeException bre = (BaseRuntimeException) thrown;

            errorAttributes.put("code", bre.getMsgCd());
            errorAttributes.put("message", Errors.getCdMsg(bre.getMsgCd(), bre.getMsgArgs()));
        }

        return errorAttributes;
    }
}
