package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;

import java.util.List;

public interface BoardMyPageRepository {
    List<BoardOwnResponse> findOwnBoardOrderByCreateDate(String nickname, Integer page);
    List<BoardMyLikedResponse> findMyLikedBoardOrderByCreateDate(String nickname, Integer page);
}
