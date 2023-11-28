package com.team.twodari.board.service;

import static com.team.twodari.board.entity.BoardLocation.*;
import static com.team.twodari.global.util.SliceConverter.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.twodari.board.repository.BoardRepository;
import com.team.twodari.platform.dto.BoardDateDto;
import com.team.twodari.platform.dto.BoardPointDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardSearchService {

	private final BoardRepository boardRepository;

	public Slice<BoardDateDto> findOrderByCreateDate(Pageable pageable) {
		List<BoardDateDto> boardList =
			boardRepository.findOrderByCreateDate(pageable.getOffset(), pageable.getPageSize(), EMPTY);

		return toSlice(boardList, pageable.getPageNumber());
	}

	public Slice<BoardPointDto> findOrderByPoint(Pageable pageable) {
		List<BoardPointDto> boardList =
			boardRepository.findOrderByPoint(pageable.getOffset(), pageable.getPageSize(), EMPTY);

		return toSlice(boardList, pageable.getPageNumber());
	}

	public Slice<BoardDateDto> findOrderByCreateDateWithLocation(Pageable pageable, String location) {
		List<BoardDateDto> boardList =
			boardRepository.findOrderByCreateDate(pageable.getOffset(), pageable.getPageSize(), valueOf(location));

		return toSlice(boardList, pageable.getPageNumber());
	}

	public Slice<BoardPointDto> findOrderByPointWithLocation(Pageable pageable, String location) {

		List<BoardPointDto> boardList =
			boardRepository.findOrderByPoint(pageable.getOffset(), pageable.getPageSize(),
				getLocation(location));

		return toSlice(boardList, pageable.getPageNumber());
	}

}
