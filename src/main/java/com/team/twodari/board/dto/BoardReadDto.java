package com.team.twodari.board.dto;

import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.subBoard.entity.SubBoardEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardReadDto {

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotBlank
    private String introduce;

    private List<SubBoardEntity> subBoards;

    public static BoardReadDto fromEntity(BoardEntity entity, List<SubBoardEntity> subBoards) {
        return new BoardReadDto(entity.getUser().getNickname(), entity.getTitle(), entity.getIntroduce(), subBoards);
    }
}
