package com.team.twodari.board.controller;

import com.team.twodari.board.controller.facade.BoardFacadeService;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.common.ComResponseEntity;
import com.team.twodari.common.dto.ComResponseDTO;
import com.team.twodari.common.dto.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@CrossOrigin
public class BoardMyPageController {
    private final BoardFacadeService boardService;
    public static String nickname = "테스터";

    @GetMapping("/mypage/own")
    public ComResponseEntity<PageResponse> findOwnBoardOrderByCreateDate(
            @RequestParam(defaultValue = "0") Integer page)
//            @AuthenticationPrincipal User user)
    {
        PageResponse<List<BoardOwnResponse>> response = boardService.findOwnBoardOrderByCreateDate(nickname, page);
        return new ComResponseEntity<>(new ComResponseDTO<>("내가 쓴 글목록", response));
    }

    @GetMapping("/mypage/like")
    public ComResponseEntity<PageResponse> findMyLikedBoardOrderByCreateDate(
            @RequestParam(defaultValue = "0") Integer page,
            @AuthenticationPrincipal User user)
    {
        PageResponse responses = boardService.findMyLikedBoardOrderByCreateDate(nickname, page);
        return new ComResponseEntity<>(new ComResponseDTO<>("내가 쓴 글목록", responses));
    }
}
