package com.team.twodari.admin.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "MP_USER_ROLE")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    @Column(name="USER_ROLE_SEQ")
    public Integer userRoleSeq;

    @Column(name="USER_SEQ")
    public Integer userSeq;

    @Column(name="ROLE_SEQ")
    public Integer roleSeq;
}
