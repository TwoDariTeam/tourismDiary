package com.team.twodari.board.service;

import static com.team.twodari.global.util.SliceConverter.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardSearchService {

	private final BoardRepository boardRepository;

	public Slice<BoardEntityDto> findOrderByCreateDate(Pageable pageable) {
		List<BoardEntityDto> boardList =
			boardRepository.findOrderByCreateDate(pageable.getOffset(), pageable.getPageSize());

		return toSlice(boardList, pageable.getPageNumber());
	}

	public Slice<BoardEntityDto> findOrderByLike(Pageable pageable, Integer condition, String type) {
		List<BoardEntityDto> boardList =
			boardRepository.findOrderByPoint(pageable.getOffset(), pageable.getPageSize(), condition, type);

		return toSlice(boardList, pageable.getPageNumber());
	}
}
