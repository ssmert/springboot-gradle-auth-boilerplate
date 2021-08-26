package com.example.demo.role.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.example.demo.core.domain.DomainEntity;
import com.example.demo.core.domain.YesOrNo;
import com.example.demo.menu.domain.Menu;
import com.example.demo.role.api.dto.RoleRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * 역할 도메인 엔티티
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_ROLE", uniqueConstraints = { @UniqueConstraint(name = "U_ROLE_CODE", columnNames = { "CODE" }) })
public class Role extends DomainEntity {
    /**
     * 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long roleId;

    /**
     * 코드
     */
    @NonNull
    @Column(name = "CODE", length = 100, nullable = false)
    private String roleCode;

    /**
     * 명
     */
    @NonNull
    @Column(name = "NAME", length = 50, nullable = false)
    private String roleNm;

    /**
     * 역할 권한 목록
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "TB_ROLE_AUTH",
            joinColumns = @JoinColumn(name = "ROLE_ID", nullable = false, insertable = false, updatable = false, unique = false),
            inverseJoinColumns = @JoinColumn(name = "MENU_ID", nullable = false, insertable = false, updatable = false, unique = false))
    private Set<Menu> roleAuths = Sets.newLinkedHashSet();

    public List<Menu> getRoleAuths() {
        return Lists.newArrayList(this.roleAuths);
    }

    /**
     * 수정
     *
     * @param req 요청 데이터
     */
    public void edit(RoleRequest req) {
        this.roleCode = req.getRoleCode();
        this.roleNm = req.getRoleNm();
        this.useYn = req.getUseYn();
    }

    /**
     * 삭제
     */
    public void delete() {
        this.useYn = YesOrNo.No;
        this.delYn = YesOrNo.Yes;
    }

    /**
     * 역할권한 목록을 적용한다.
     *
     * @param roleAuthList 역할권한 목록
     */
    public void applyRoleAuthList(Set<Menu> roleAuthList) {
        if (!this.roleAuths.isEmpty()) {
            this.roleAuths.clear();
        }
        this.roleAuths.addAll(roleAuthList);
    }
}
