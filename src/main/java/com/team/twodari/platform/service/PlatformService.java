package com.team.twodari.platform.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.service.BoardSearchService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlatformService {

	private final BoardSearchService boardSearchService;

	public Slice<BoardEntityDto> getMainPageData(Pageable pageable) {

		return boardSearchService.findOrderByCreateDate(pageable);
	}

	public Slice<BoardEntityDto> getPageOrderByPoint(Pageable pageable) {

		return boardSearchService.findOrderByPoint(pageable);
	}

	public Slice<BoardEntityDto> getPageOrderByDate(Pageable pageable, String location) {

		return boardSearchService.findOrderByCreateDateWithLocation(pageable, location);
	}

	public Slice<BoardEntityDto> getPageOrderByPoint(Pageable pageable, String location) {

		return boardSearchService.findOrderByPointWithLocation(pageable, location);
	}

}
