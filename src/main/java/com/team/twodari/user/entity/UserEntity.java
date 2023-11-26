package com.team.twodari.user.entity;

import com.team.twodari.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "TB_USER")
@Entity
@Builder
@ToString
@Getter
@Setter
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
    public static class UserEntityBuilder {
        public UserEntity build() {
            UserEntity userEntity = new UserEntity();
            userEntity.email = email;
            userEntity.nickname = nickname;
            userEntity.password = password;
            userEntity.createName = email; // createName을 email로 초기화
            return userEntity;
        }
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
