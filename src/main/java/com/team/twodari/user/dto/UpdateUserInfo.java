package com.team.twodari.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateUserInfo {
    @NotBlank
    @Max(40)
    @Min(18)
    private  String email;

    @NotBlank
    @Max(40)
    @Min(4)
    private String nickname;
}
