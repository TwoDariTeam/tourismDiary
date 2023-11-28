package com.team.twodari.platform.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.team.twodari.board.service.BoardSearchService;
import com.team.twodari.platform.dto.BoardDateDto;
import com.team.twodari.platform.dto.BoardPointDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlatformService {

	private final BoardSearchService boardSearchService;

	public Slice<BoardDateDto> getMainPageData(Pageable pageable) {

		return boardSearchService.findOrderByCreateDate(pageable);
	}

	public Slice<BoardDateDto> getPageOrderByDate(Pageable pageable, String location) {

		return boardSearchService.findOrderByCreateDateWithLocation(pageable, location);
	}

	public Slice<BoardPointDto> getPageOrderByPoint(Pageable pageable) {

		return boardSearchService.findOrderByPoint(pageable);
	}

	public Slice<BoardPointDto> getPageOrderByPoint(Pageable pageable, String location) {

		return boardSearchService.findOrderByPointWithLocation(pageable, location);
	}

}
