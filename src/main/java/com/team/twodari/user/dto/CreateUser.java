package com.team.twodari.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
//레코드 쓰면 17버전 이상
//All 차이점 requeied차이점 알아보기
//레코드 불변 객체. (.?)
public class CreateUser {
    @NotBlank
    @Max(40)
    @Min(1)
    private final String email;

    @NotBlank
    @Max(40)
    @Min(1)
    private final String nickname;

    @NotBlank
    @Max(24)
    @Min(1)
    private final String password;

}
