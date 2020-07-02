package com.example.demo.user.domain;


import com.example.demo.core.domain.DomainEntity;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.role.domain.Role;
import com.example.demo.user.api.transferobject.UserRequest;
import com.google.common.collect.Sets;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

/**
 * 사용자 엔티티이다.
 *
 * @author jonghyeon
 */
@Entity
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_USER", uniqueConstraints = {@UniqueConstraint(name = "U_USER_ID", columnNames = {"USER_ID"})})
public class User extends DomainEntity {
    /**
     * 사용자일련번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_SEQ")
    private Long userSeq;

    /**
     * 사용자식별자
     */
    @NonNull
    @Column(name = "USER_ID", length = 100, nullable = false)
    private String userId;

    /**
     * 사용자명
     */
    @NonNull
    @Column(name = "USER_NM", length = 50, nullable = false)
    private String userNm;

    /**
     * 비밀번호
     */
    @NonNull
    @Column(name = "USER_PWD", nullable = false)
    private String userPwd;

    /**
     * 휴대전화
     */
    @NonNull
    @Column(name = "USER_PHONE", length = 20, nullable = false)
    private String userPhone;

    /**
     * 유선번호
     */
    @NonNull
    @Column(name = "USER_TEL", length = 20)
    private String userTel;

    /**
     * 이메일
     */
    @NonNull
    @Column(name = "USER_EMAIL", length = 100, nullable = false)
    private String userEmail;

    /**
     * 부서
     */
    @NonNull
    @Column(name = "USER_DEPT", length = 50, nullable = false)
    private String userDept;

    /**
     * 사용여부
     */
    @NonNull
    @Column(name = "USER_USE_YN", nullable = false, columnDefinition = "char(1)")
    private YesOrNo userUseYn = YesOrNo.Yes;

    /**
     * 최종접속시간
     */
    @Column(name = "USER_LST_CNN_DT", nullable = false)
    private LocalDateTime userLstCnnDt = LocalDateTime.now();

    /**
     * 사용자역할 목록
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "TB_USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_SEQ", nullable = false, insertable = false, updatable = false, unique = false),
            inverseJoinColumns = @JoinColumn(name = "ROLE_SEQ", nullable = false, insertable = false, updatable = false, unique = false))
    private Set<Role> userRoles = Sets.newHashSet();

    /**
     * 사용자를 수정한다.
     *
     * @param req 사용자 요청데이터
     */
    public void edit(UserRequest req) {
        this.userNm = req.getUserNm();
        this.userPwd = req.getUserPwd();
        this.userPhone = req.getUserPhone();
        this.userTel = req.getUserTel();
        this.userEmail = req.getUserEmail();
        this.userDept = req.getUserDept();
        this.userUseYn = req.getUserUseYn();
    }

    /**
     * 역할을 부여한다.
     *
     * @param roleList 역할목록
     */
    public void assignRoles(Collection<Role> roleList) {
        this.userRoles.addAll(roleList);
    }
}
