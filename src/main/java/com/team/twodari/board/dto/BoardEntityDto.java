package com.team.twodari.board.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;
import com.team.twodari.board.entity.BoardLocation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntityDto {

	private Long boardSeq;
	private Long categorySeq;
	private String author;
	private String title;
	private Integer totalPoint;
	private LocalDateTime createTime;
	private BoardLocation location;

	@QueryProjection
	public BoardEntityDto(Long boardSeq, Long categorySeq, String author, String title, Integer totalPoint, LocalDateTime createTime, BoardLocation location) {
		this.boardSeq = boardSeq;
		this.categorySeq = categorySeq;
		this.author = author;
		this.title = title;
		this.totalPoint = totalPoint;
		this.createTime = createTime;
		this.location = location;
	}
}
