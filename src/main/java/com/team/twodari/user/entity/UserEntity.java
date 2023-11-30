package com.team.twodari.user.entity;

import com.team.twodari.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "tb_user")
@Entity
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {
    // 유저 일련번호
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long userSeq;

    // 이메일
    @NotBlank
    @Column(columnDefinition = "VARCHAR(50)")
    private String email;

    // 닉네임
    @NotBlank
    @Column(columnDefinition = "VARCHAR(40)")
    private String nickname;

    // 패스워드
    @NotBlank
    @Column(columnDefinition = "VARCHAR(128)")
    private String password;

    // 삭제플래그.
    @Column(columnDefinition = "CHAR(1)", insertable = false)
    private String deleted;


    private String createName;

    @Builder
    public UserEntity(String email, String nickname, String password , String createName) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.createName = createName;
    }


    public void softDelete() {
        this.deleted = "Y";
    }


    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }


}
