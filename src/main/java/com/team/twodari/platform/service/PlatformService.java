package com.team.twodari.platform.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.service.BoardSearchService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class PlatformService {

	private final BoardSearchService boardSearchService;

	public Slice<BoardEntityDto> getMainPageData(Pageable pageable) {

		return boardSearchService.findOrderByCreateDate(pageable);
	}

	public Slice<BoardEntityDto> getMainPageDataByCondition(Pageable pageable, Integer condition, String type) {

		return boardSearchService.findOrderByLike(pageable, condition, type);
	}

}
