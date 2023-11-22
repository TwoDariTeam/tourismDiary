package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardEntityDto;
import org.springframework.data.domain.Slice;

public interface BoardSearchRepository {

    Slice<BoardEntityDto> findContains(Integer page, String word);

    Slice<BoardEntityDto> findOrderByCreateDate(Integer page);
    Slice<BoardEntityDto> findOrderByPoint(Integer page);
}
