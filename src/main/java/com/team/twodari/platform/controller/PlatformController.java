package com.team.twodari.platform.controller;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.common.ComResponseEntity;
import com.team.twodari.common.dto.ComResponseDTO;
import com.team.twodari.platform.service.PlatformService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlatformController {


    private final PlatformService platformService;
    @GetMapping("/")
    //얘는 무조건 기본값만
    public Slice<BoardEntityDto> main(@RequestParam(defaultValue = "0") @NotBlank Integer page,
                                      @RequestParam(defaultValue = "0") Integer condition) {
        return platformService.getMainPageData(page,condition);
    }

    @GetMapping("/target/{condition}")
    public ComResponseEntity<BoardEntityDto> choiceMainList(@PathVariable(value = "condition")Integer condition){
        return new ComResponseEntity<>(new ComResponseDTO<>());
    }






}
