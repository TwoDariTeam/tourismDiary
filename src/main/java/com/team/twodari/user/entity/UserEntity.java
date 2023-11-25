package com.team.twodari.user.entity;

import com.team.twodari.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
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
    @Null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_SEQ", columnDefinition = "INT")
    private Long userSeq;

    // 이메일
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL", columnDefinition = "VARCHAR(50)")
    private String email;

    // 닉네임
    @NotBlank
    @Size(min = 1, max = 40)
    @Column(name="NICKNAME", columnDefinition = "VARCHAR(40)")
    private String nickname;

    // 패스워드
    @NotBlank
    @Size(min = 1, max = 128)
    @Column(name="PASSWORD", columnDefinition = "VARCHAR(128)")
    private String password;

    // 삭제플래그.
    @Null
    @Column(name="DELETED", columnDefinition = "CHAR(1)")
    private String deleted = "N";

    //비밀번호 변경
    public void updateUserEntity(String password) {
        this.password = password;
    }

    //회원 유저 소프트 딜리트
    public void softDelete(){
        this.deleted = "Y";
    }

    public  boolean isDeleted(){
        return "Y".equals(this.deleted);
    }
}
