package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardEntityDto;
import org.springframework.data.domain.Slice;

public interface BoardSearchRepository {
    Slice<BoardEntityDto> findOrderByCreateDate(int page);
    Slice<BoardEntityDto> findOrderByLike(int page);
}
