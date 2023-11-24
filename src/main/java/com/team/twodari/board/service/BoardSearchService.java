package com.team.twodari.board.service;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardSearchService {

    private final BoardRepository boardRepository;

    public Slice<BoardEntityDto> findOrderByCreateDate(Integer page) {
        return boardRepository.findOrderByCreateDate(page);
    }

    public Slice<BoardEntityDto> findOrderByLike(Integer page) {
        return boardRepository.findOrderByPoint(page);
    }
}
