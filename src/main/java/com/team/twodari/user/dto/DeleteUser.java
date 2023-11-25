package com.team.twodari.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteUser {
    @NotBlank
    @Max(40)
    @Min(18)
    private final String email;

    @NotBlank
    @Max(24)
    @Min(8)
    private final String password;
}
