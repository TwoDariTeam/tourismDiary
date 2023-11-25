package com.team.twodari.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardOwnResponse {
    private Long boardSeq;
    private Long categorySeq;
    private String author;
    private String title;
    private Integer totalPoint;
    private List<String> tags;

    public BoardOwnResponse(Long boardSeq, Long categorySeq, String author, String title, Integer totalPoint) {
        this.boardSeq = boardSeq;
        this.categorySeq = categorySeq;
        this.author = author;
        this.title = title;
        this.totalPoint = totalPoint;
    }

    public void connectTags(List<String> tags){
        this.tags = tags;
    }
}
