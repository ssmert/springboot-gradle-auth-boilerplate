package com.example.demo.role.domain;

import com.example.demo.core.domain.DomainEntity;
import com.example.demo.core.infrastructure.constant.YesOrNo;
import com.example.demo.role.api.transferobject.RoleRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 역할 엔티티이다.
 *
 * @author jonghyeon
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_ROLE", uniqueConstraints = {@UniqueConstraint(name = "U_ROLE_ID", columnNames = {"ROLE_ID"})})
public class Role extends DomainEntity {
    /**
     * 역할일련번호
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_SEQ")
    private Long roleSeq;

    /**
     * 역할식별자
     */
    @NonNull
    @Column(name = "ROLE_ID", length = 100, nullable = false)
    private String roleId;

    /**
     * 역할명
     */
    @NonNull
    @Column(name = "ROLE_NM", length = 50, nullable = false)
    private String roleNm;

    /**
     * 사용여부
     */
    @NonNull
    @Column(name = "ROLE_USE_YN", nullable = false, columnDefinition = "char(1)")
    private YesOrNo roleUseYn;

    public void edit(RoleRequest req) {
        this.roleId = req.getRoleId();
        this.roleNm = req.getRoleNm();
        this.roleUseYn = req.getRoleUseYn();
    }
}
