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

    // 이미지 나중에 추가

    private List<SubBoardEntity> subBoards;

    public static BoardReadDto fromEntity(BoardEntity entity, List<SubBoardEntity> subBoards) {
        return new BoardReadDto(entity.getAuthor(), entity.getTitle(), subBoards);
    }
}
