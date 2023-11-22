package com.team.twodari.board.service;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardSearchService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Slice<BoardEntityDto> findOrderByCreateDate(int page) {
        return boardRepository.findOrderByCreateDate(page);
    }

    @Transactional(readOnly = true)
    public Slice<BoardEntityDto> findOrderByLike(int page) {
        return boardRepository.findOrderByLike(page);
    }
}
