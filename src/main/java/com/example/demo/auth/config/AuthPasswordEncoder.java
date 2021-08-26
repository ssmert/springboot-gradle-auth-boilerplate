package com.example.demo.auth.config;

import com.example.demo.auth.util.CryptoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 비밀번호 인코더
 */
@Slf4j
@Component
public class AuthPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return CryptoUtil.getSHA512(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String reqPwd = CryptoUtil.getSHA512(rawPassword.toString());
        return reqPwd.equals(encodedPassword);
    }
}
