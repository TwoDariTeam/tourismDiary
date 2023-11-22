package com.team.twodari.board.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardEntityDto  {

    private Long boardSeq;
    private Long categorySeq;
    private String author;
    private String title;
    private Integer totalPoint;
}
