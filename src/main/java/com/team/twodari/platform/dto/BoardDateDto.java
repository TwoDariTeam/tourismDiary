package com.team.twodari.platform.dto;

import java.time.LocalDateTime;

import com.team.twodari.board.entity.BoardLocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class BoardDateDto {

	private Long boardSeq;
	private Long categorySeq;
	private String author;
	private String title;
	private Integer totalPoint;
	private String content;
	private String imageUrl;
	private LocalDateTime createTime;
	private BoardLocation location;

	public BoardDateDto(Long boardSeq, Long categorySeq, String author, String title, Integer totalPoint, String content, LocalDateTime createTime, BoardLocation location) {
		this.boardSeq = boardSeq;
		this.categorySeq = categorySeq;
		this.author = author;
		this.title = title;
		this.totalPoint = totalPoint;
		this.content = content;
		this.createTime = createTime;
		this.location = location;
	}

	public void insertImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
