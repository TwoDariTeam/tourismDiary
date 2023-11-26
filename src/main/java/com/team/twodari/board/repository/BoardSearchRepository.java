package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardEntityDto;

import java.util.List;

public interface BoardSearchRepository {

    List<BoardEntityDto> findContains(Integer offset,Integer size, String word);

    List<BoardEntityDto> findOrderByCreateDate(Long offset,Integer size);

    List<BoardEntityDto> findOrderByPoint(Long offset,Integer size,Integer condition, String type);
}
