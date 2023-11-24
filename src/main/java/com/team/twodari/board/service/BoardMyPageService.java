package com.team.twodari.board.service;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardMyPageService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardOwnResponse> findOwnBoardOrderByCreateDate(String nickname, Integer page) {
        return boardRepository.findOwnBoardOrderByCreateDate(nickname, page);
    }

    @Transactional(readOnly = true)
    public List<BoardMyLikedResponse> findMyLikedBoardOrderByCreateDate(String nickname, Integer page) {
        return boardRepository.findMyLikedBoardOrderByCreateDate(nickname, page);
    }
}
