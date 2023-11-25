package com.team.twodari.subBoard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SubBoardReadDto {

    @NotBlank
    private String contents;

    // 이미지 나중에 추가
}
