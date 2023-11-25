package com.team.twodari.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardCreateDto {

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotNull
    private Integer accessRole;
}