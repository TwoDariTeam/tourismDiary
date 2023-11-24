package com.team.twodari.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "MP_USER_ROLE")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleEntity {
    @Id
    @Column(name = "USER_ROLE_SEQ")
    private Long userRoleSeq;

    @Column(name = "USER_SEQ")
    private Long userSeq;

    @Column(name = "ROLE_SEQ")
    private Long roleSeq;

}
