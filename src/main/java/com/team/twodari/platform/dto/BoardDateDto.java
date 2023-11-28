package com.team.twodari.platform.dto;

import java.time.LocalDateTime;

import com.team.twodari.board.entity.BoardLocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
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

}
