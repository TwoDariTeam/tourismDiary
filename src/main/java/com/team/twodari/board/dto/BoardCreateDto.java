package com.team.twodari.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String introduce;
}
