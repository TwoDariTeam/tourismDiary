package com.team.twodari.image.entity;

import com.team.twodari.common.entity.BaseEntity;
import com.team.twodari.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_USER_IMAGE")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long userImageSeq;

    @Column(columnDefinition = "VARCHAR(200)")
    private String path;

    @Column(columnDefinition = "CHAR(1)")
    private String deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", nullable = false)
    private UserEntity user;

    public void deleteEntity() {
        this.deleted = "Y";
    }
}
