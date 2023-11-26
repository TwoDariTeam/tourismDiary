package com.team.twodari.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MP_USER_ROLE")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_SEQ")
    private Long userRoleSeq;

    @Column(name = "USER_SEQ")
    private Long userSeq;

    @Column(name = "ROLE_SEQ")
    private Long roleSeq;

}
