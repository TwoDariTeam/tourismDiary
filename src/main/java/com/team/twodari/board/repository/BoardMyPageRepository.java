package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardMyPageRepository {
    Page<BoardOwnResponse> findOwnBoardOrderByCreateDate(String nickname, Integer page);
    Page<BoardMyLikedResponse> findMyLikedBoardOrderByCreateDate(String nickname, Integer page);
}
