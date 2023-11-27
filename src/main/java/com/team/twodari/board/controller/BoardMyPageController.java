package com.team.twodari.board.controller;

import com.team.twodari.board.controller.facade.BoardFacadeService;
import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.common.ComResponseEntity;
import com.team.twodari.common.dto.ComResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@CrossOrigin
public class BoardMyPageController {
    private final BoardFacadeService boardService;
    public static String nickname = "테스터";

    @GetMapping("/mypage/own")
    public ComResponseEntity<Slice<BoardOwnResponse>> findOwnBoardOrderByCreateDate(
            @RequestParam(defaultValue = "0") Integer page)
//            @AuthenticationPrincipal User user)
    {
        Slice<BoardOwnResponse> responses = boardService.findOwnBoardOrderByCreateDate(nickname, page);
        return new ComResponseEntity<>(new ComResponseDTO<>("내가 쓴 글목록", responses));
    }

    @GetMapping("/mypage/like")
    public ComResponseEntity<Slice<BoardMyLikedResponse>> findMyLikedBoardOrderByCreateDate(
            @RequestParam(defaultValue = "0") Integer page,
            @AuthenticationPrincipal User user)
    {
        Slice<BoardMyLikedResponse> responses = boardService.findMyLikedBoardOrderByCreateDate(nickname, page);
        return new ComResponseEntity<>(new ComResponseDTO<>("내가 쓴 글목록", responses));
    }
}
