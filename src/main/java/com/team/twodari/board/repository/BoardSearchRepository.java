package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardEntityDto;

import java.util.List;

public interface BoardSearchRepository {

    List<BoardEntityDto> findContains(Integer page, String word);

    List<BoardEntityDto> findOrderByCreateDate(Integer page);

    List<BoardEntityDto> findOrderByPoint(Integer page);
}
