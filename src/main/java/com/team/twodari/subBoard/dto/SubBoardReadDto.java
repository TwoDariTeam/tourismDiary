package com.team.twodari.subBoard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubBoardReadDto {

    @NotBlank
    private String contents;

    // 이미지 나중에 추가
}
