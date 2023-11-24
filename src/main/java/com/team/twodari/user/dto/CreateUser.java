package com.team.twodari.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUser {
    private String email;
    private String nickname;
    private String password;

}
