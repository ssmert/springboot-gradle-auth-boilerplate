package com.example.demo.user.api.transferobject;


import com.example.demo.user.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 사용자 어그리게이션 TO객체 변환기이다.
 *
 * @author jonghyeon
 */
@Component
public class UserConverter {
    public UserResponse convert(User user) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }
}
