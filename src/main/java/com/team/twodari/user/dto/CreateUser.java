package com.team.twodari.user.dto;

import com.team.twodari.common.constant.RegularExpression;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
//레코드 쓰면 17버전 이상
//All 차이점 requeied차이점 알아보기
//레코드 불변 객체. (.?)
public class CreateUser {
    @NotBlank
    @Email
    private  String email;

    @NotBlank
    @Max(40)
    @Min(1)
    private  String nickname;

    @NotBlank
    @Pattern(regexp = RegularExpression.PASSWORD_NUM_LOW_ENG_UPP_ENG_PATTERN)
    private  String password;
    @Null
    private  String createName;


    public void changeName(String email) {
        this.createName = email;
    }


}
