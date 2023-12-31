package com.team.twodari.subBoard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SubBoardCreateDto {

    @NotNull
    private Long boardSeq;

    @NotBlank
    private String contents;
}
