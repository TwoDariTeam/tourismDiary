package com.team.twodari.platform.controller;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.platform.service.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlatformController {


    private final PlatformService platformService;
    @GetMapping("/")
    public Slice<BoardEntityDto> main(@RequestParam(defaultValue = "0") Integer page,
                                      @RequestParam(defaultValue = "0") Integer condition) {
        return platformService.getMainPageData(page,condition);
    }







}
