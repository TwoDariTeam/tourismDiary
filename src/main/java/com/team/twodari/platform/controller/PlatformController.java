package com.team.twodari.platform.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.twodari.platform.dto.BoardDateDto;
import com.team.twodari.platform.dto.BoardPointDto;
import com.team.twodari.platform.service.PlatformService;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PlatformController {

	private final PlatformService platformService;

	@GetMapping("/")
	public Slice<BoardDateDto> mainPage(@PageableDefault(size = 20) Pageable pageable) {
		return platformService.getMainPageData(pageable);
	}

	@GetMapping("/point")
	public Slice<BoardPointDto> getMainBoardsByBoard(
		@PageableDefault(size = 20) Pageable pageable) {

		return platformService.getPageOrderByPoint(pageable);
	}

	@GetMapping("/main/date")
	public Slice<BoardDateDto> getMarkerDataOrderByDate(
		@PageableDefault(size = 20) Pageable pageable,
		@RequestParam(defaultValue = "seoul") @NotBlank String location) {

		return platformService.getPageOrderByDate(pageable, location);
	}

	@GetMapping("/main/point")
	public Slice<BoardPointDto> getMarkerDataOrderByPoint(
		@PageableDefault(size = 20) Pageable pageable,
		@RequestParam(defaultValue = "SEOUL") @NotBlank String location) {

		return platformService.getPageOrderByPoint(pageable, location);
	}

}
