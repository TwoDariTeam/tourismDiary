package com.team.twodari.board.repository;

import com.team.twodari.platform.dto.BoardContainsStringDto;
import com.team.twodari.platform.dto.BoardDateDto;
import com.team.twodari.platform.dto.BoardPointDto;
import com.team.twodari.board.entity.BoardLocation;

import java.util.List;

public interface BoardSearchRepository {


    List<BoardContainsStringDto> findContains(Integer offset,Integer size, BoardLocation location, String word);

    List<BoardDateDto> findOrderByCreateDate(Long offset,Integer size,BoardLocation location);

    List<BoardPointDto> findOrderByPoint(Long offset, Integer size, BoardLocation location);





}
