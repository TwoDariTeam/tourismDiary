package com.team.twodari.user.entity;

import com.team.twodari.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

@Table(name = "TB_USER")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {
    // 유저 일련번호
    @Null
    @Id
    @Column(columnDefinition = "INT")
    private Long userSeq;

    // 이메일
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(columnDefinition = "VARCHAR(50)")
    private String email;

    // 닉네임
    @NotBlank
    @Size(min = 1, max = 40)
    @Column(columnDefinition = "VARCHAR(40)")
    private String nickname;

    // 패스워드
    @NotBlank
    @Size(min = 1, max = 128)
    @Column(columnDefinition = "VARCHAR(128)")
    private String password;

    // 삭제플래그.
    @Null
    @Column(columnDefinition = "CHAR(1)")
    private String deleted;


}
