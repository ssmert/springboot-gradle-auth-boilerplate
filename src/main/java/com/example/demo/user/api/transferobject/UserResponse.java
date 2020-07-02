package com.example.demo.user.api.transferobject;

import com.example.demo.core.api.transferobject.BaseResponseDto;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.core.infrastructure.json.DateYmsDeserializer;
import com.example.demo.core.infrastructure.json.DateYmsSerializer;
import com.example.demo.role.domain.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pojomatic.annotations.AutoProperty;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 사용자 응답데이터이다.
 *
 * @author jonghyeon
 */
@Getter
@Setter
@AutoProperty
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends BaseResponseDto {
    /**
     * 사용자식별자
     */
    private String userId;

    /**
     * 사용자명
     */
    private String userNm;

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
     * 최종접속시간
     */
    @JsonSerialize(using = DateYmsSerializer.class)
    @JsonDeserialize(using = DateYmsDeserializer.class)
    private LocalDateTime userLstCnnDt;

    /**
     * 사용자역할 목록
     */
    private Set<Role> userRoles = Sets.newHashSet();
}

