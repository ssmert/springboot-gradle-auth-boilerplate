package com.example.demo.user.api.transferobject;


import com.example.demo.core.api.transferobject.BaseRequestDto;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

import java.util.List;

/**
 * 사용자 요청데이터이다.
 *
 * @author jonghyeon
 */
@Getter
@Setter
@AutoProperty
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends BaseRequestDto {
    /**
     * 사용자식별자
     */
    private String userId;

    /**
     * 사용자명
     */
    private String userNm;

    /**
     * 비밀번호
     */
    private String userPwd;

    /**
     * 휴대전화
     */
    private String userPhone;

    /**
     * 유선번호
     */
    private String userTel;

    /**
     * 이메일
     */
    private String userEmail;

    /**
     * 부서
     */
    private String userDept;

    /**
     * 사용여부
     */
    private YesOrNo userUseYn;

    /**
     * 역할식별자목록
     */
    private List<String> roleIdList = Lists.newArrayList();
}

