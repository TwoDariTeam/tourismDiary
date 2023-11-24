package com.team.twodari.subBoard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SubBoardCreateDto {

    @NotNull
    private Long boardSeq;

    @NotBlank
    private String contents;
}
