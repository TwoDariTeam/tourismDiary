package com.team.twodari.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Login {
    @NotBlank
    @Max(40)
    @Min(1)
    private  String email;

    @NotBlank
    @Max(24)
    @Min(1)
    private  String password;


}
