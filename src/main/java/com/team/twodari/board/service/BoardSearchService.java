package com.team.twodari.board.service;

import static com.team.twodari.board.entity.BoardLocation.*;
import static com.team.twodari.global.util.SliceConverter.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.twodari.board.repository.BoardRepository;
import com.team.twodari.image.entity.BoardImageEntity;
import com.team.twodari.image.repository.BoardImageRepository;
import com.team.twodari.platform.dto.BoardDateDto;
import com.team.twodari.platform.dto.BoardPointDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardSearchService {

	private final BoardRepository boardRepository;
	private final BoardImageRepository boardImageRepository;


	// TODO : 공통 부분 메소드 분리 작업 필요
	public Slice<BoardDateDto> findOrderByCreateDate(Pageable pageable) {
		List<BoardDateDto> boardList =
			boardRepository.findOrderByCreateDate(pageable.getOffset(), pageable.getPageSize(), EMPTY);

		Map<Long, String> pathMap = findAndInsertImagePath(
			boardList.stream().map(BoardDateDto::getBoardSeq).toList());

		boardList.forEach(b -> {
			b.insertImageUrl(pathMap.get(b.getBoardSeq()));
		});

		return toSlice(boardList, pageable.getPageNumber());
	}

	public Slice<BoardPointDto> findOrderByPoint(Pageable pageable) {
		List<BoardPointDto> boardList =
			boardRepository.findOrderByPoint(pageable.getOffset(), pageable.getPageSize(), EMPTY);

		Map<Long, String> pathMap = findAndInsertImagePath(
			boardList.stream().map(BoardPointDto::getBoardSeq).toList());

		boardList.forEach(b -> {
			b.insertImageUrl(pathMap.get(b.getBoardSeq()));
		});

		return toSlice(boardList, pageable.getPageNumber());
	}

	public Slice<BoardDateDto> findOrderByCreateDateWithLocation(Pageable pageable, String location) {
		List<BoardDateDto> boardList =
			boardRepository.findOrderByCreateDate(pageable.getOffset(), pageable.getPageSize(), valueOf(location));

		Map<Long, String> pathMap = findAndInsertImagePath(
			boardList.stream().map(BoardDateDto::getBoardSeq).toList());

		boardList.forEach(b -> {
			b.insertImageUrl(pathMap.get(b.getBoardSeq()));
		});

		return toSlice(boardList, pageable.getPageNumber());
	}

	public Slice<BoardPointDto> findOrderByPointWithLocation(Pageable pageable, String location) {

		List<BoardPointDto> boardList =
			boardRepository.findOrderByPoint(pageable.getOffset(), pageable.getPageSize(),
				getLocation(location));

		Map<Long, String> pathMap = findAndInsertImagePath(
			boardList.stream().map(BoardPointDto::getBoardSeq).toList());

		boardList.forEach(b -> {
			b.insertImageUrl(pathMap.get(b.getBoardSeq()));
		});

		return toSlice(boardList, pageable.getPageNumber());
	}

	private Map<Long, String> findAndInsertImagePath(List<Long> boardSeq) {
		return boardImageRepository.findFirstByBoardSeqs(boardSeq)
			.stream().collect(Collectors.toMap(
				image -> image.getBoard().getBoardSeq(),
				BoardImageEntity::getPath
			));
	}

}
