package com.team.twodari.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePassword {
    private String email;
    private  String password;
    private String newPassword;
}
