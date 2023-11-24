package com.team.twodari.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardMyLikedResponse {
    private Long boardSeq;
    private Long categorySeq;
    private String author;
    private String title;
    private Integer totalPoint;
    private List<String> tags;

    public BoardMyLikedResponse(Long boardSeq, Long categorySeq, String author, String title, Integer totalPoint) {
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
