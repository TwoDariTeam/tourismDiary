package com.team.twodari.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
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
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private LocalDateTime writeDate;
    private String introduce;
    private List<String> imageUrls;

    public BoardMyLikedResponse(Long boardSeq, Long categorySeq, String author,
                            String title, Integer totalPoint, LocalDateTime writeDate,
                            String introduce) {
        this.boardSeq = boardSeq;
        this.categorySeq = categorySeq;
        this.author = author;
        this.title = title;
        this.totalPoint = totalPoint;
        this.writeDate = writeDate;
        this.introduce = introduce;
    }

    public void connectTags(List<String> tags){
        this.tags = tags;
    }

    public void connectImageUrls(List<String> imageUrls){
        this.imageUrls = imageUrls;
    }
}
