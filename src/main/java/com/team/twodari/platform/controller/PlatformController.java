package com.team.twodari.platform.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.platform.service.PlatformService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PlatformController {

	private final PlatformService platformService;

	@GetMapping("/")
	public Slice<BoardEntityDto> mainPage(@PageableDefault(size = 20) Pageable pageable) {
		return platformService.getMainPageData(pageable);
	}

	@GetMapping("/main/boards")
	public Slice<BoardEntityDto> getMainBoards(
		@PageableDefault(size = 20) Pageable pageable,
		@RequestParam(defaultValue = "0") Integer condition,
		@RequestParam(defaultValue = "seoul") String location
	) {

		return platformService.getMainPageDataByCondition(pageable, condition, location);
	}

}
