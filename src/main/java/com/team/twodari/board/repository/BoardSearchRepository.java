package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.entity.BoardLocation;

import java.util.List;

public interface BoardSearchRepository {


    List<BoardEntityDto> findContains(Integer offset,Integer size, BoardLocation location, String word);

    List<BoardEntityDto> findOrderByCreateDate(Long offset,Integer size,BoardLocation location);

    List<BoardEntityDto> findOrderByPoint(Long offset,Integer size,BoardLocation location);



}
