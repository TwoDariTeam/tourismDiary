package com.team.twodari.common.dto;

import lombok.*;


@ToString
@Getter
@RequiredArgsConstructor
public class TokenDTO {
    private final String access_token;
    private final String refresh_token;
    //JPA 최대 불변 최소 변화
}
