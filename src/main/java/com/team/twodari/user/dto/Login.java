package com.team.twodari.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Login {
//    @Min()
    private final String email;
    private final String password;

}
