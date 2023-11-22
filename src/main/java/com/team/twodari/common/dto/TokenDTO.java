package com.team.twodari.common.dto;

import lombok.*;


@ToString
@Getter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TokenDTO {
    private String access_token;
    private String refresh_token;
    //JPA 최대 불변 최소 변화
}
