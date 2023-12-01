package com.team.twodari.subBoard.dto;

import com.team.twodari.image.entity.SubBoardImageEntity;
import com.team.twodari.subBoard.entity.SubBoardEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SubBoardReadDto {

    @NotBlank
    private String contents;

    private List<SubBoardImageEntity> subBoardImages;

    public static SubBoardReadDto fromEntity(SubBoardEntity entity) {
        return new SubBoardReadDto(entity.getContents(), entity.getImages());
    }
}
