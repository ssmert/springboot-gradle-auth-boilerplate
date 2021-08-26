package com.example.demo.auth.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 암호화 유틸
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CryptoUtil {
    /**
     * SHA512 방식으로 암호화 한다.
     *
     * @param plainValue 평문값
     *
     * @return 암호화된 값
     */
    public static String getSHA512(String plainValue) {
        String cryptValue = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            // 인스턴스 초기화, 캐릭터셋 지정
            digest.reset();
            digest.update(plainValue.getBytes(StandardCharsets.UTF_8));

            // 16진수 128바이트 값
            cryptValue = String.format("%0128x", new BigInteger(1, digest.digest()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return cryptValue;
    }

}
