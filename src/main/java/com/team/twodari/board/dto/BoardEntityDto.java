package com.team.twodari.board.dto;

import com.team.twodari.common.entity.MutableBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardEntityDto extends MutableBaseEntity {

    private Long boardSeq;
    private Long categorySeq;
    private String author;
    private String title;

}
