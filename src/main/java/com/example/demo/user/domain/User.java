package com.example.demo.user.domain;

import com.google.common.collect.Lists;
import com.example.demo.core.domain.DomainEntity;
import com.example.demo.core.domain.YesOrNo;
import com.example.demo.role.domain.Role;
import com.example.demo.user.api.dto.UserRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 사용자 도메인 엔티티
 */
@Entity
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_USER", uniqueConstraints = { @UniqueConstraint(name = "U_LOGIN_ID", columnNames = { "LOGIN_ID" }) })
public class User extends DomainEntity {
    /**
     * 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long userId;

    /**
     * 로그인 아이디
     */
    @NonNull
    @Column(name = "LOGIN_ID", length = 100, nullable = false)
    private String loginId;

    /**
     * 명
     */
    @NonNull
    @Column(name = "NAME", length = 50, nullable = false)
    private String userNm;

    /**
     * 비밀번호
     */
    @NonNull
    @Column(name = "PW", nullable = false)
    private String userPw;

    /**
     * 휴대전화
     */
    @NonNull
    @Column(name = "PHONE", length = 20, nullable = false)
    private String userPhone;

    /**
     * 최종접속시간
     */
    @Column(name = "LST_CNN_DT")
    private LocalDateTime userLstCnnDt;

    /**
     * 최종접속IP
     */
    @Column(name = "LST_CNN_IP", length = 100)
    private String userLstCnnIp;

    /**
     * 사용자역할 목록
     */
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinTable(name = "TB_USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID", nullable = false, insertable = false, updatable = false, unique = false), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", nullable = false, insertable = false, updatable = false, unique = false))
    private Set<Role> userRoles = new LinkedHashSet<>();

    /**
     * 수정
     *
     * @param req 요청데이터
     */
    public void edit(UserRequest req) {
        this.loginId = req.getLoginId();
        this.userNm = req.getUserNm();
        this.userPw = req.getUserPw();
        this.userPhone = req.getUserPhone();
        this.useYn = req.getUseYn();
    }

    public List<Role> getUserRoles() {
        return Lists.newArrayList(this.userRoles);
    }

    /**
     * 삭제
     */
    public void delete() {
        this.useYn = YesOrNo.No;
        this.delYn = YesOrNo.Yes;
    }

    /**
     * 역할을 부여한다.
     *
     * @param role 역할
     */
    public void setUserRole(Role role) {
        if (!this.userRoles.isEmpty()) {
            this.userRoles.clear();
        }
        this.userRoles.add(role);
    }

    /**
     * 최근 접속정보를 설정한다.
     *
     * @param userLstCnnDt 최근접속시간
     * @param userLstCnnIp 최근접속IP
     */
    public void setLstCnnInfo(LocalDateTime userLstCnnDt, String userLstCnnIp) {
        this.userLstCnnDt = userLstCnnDt;
        this.userLstCnnIp = userLstCnnIp;
    }

}
