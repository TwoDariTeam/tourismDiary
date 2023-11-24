package com.team.twodari.board.service;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.team.twodari.global.util.SliceConverter.toSlice;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardSearchService {

    private final BoardRepository boardRepository;

    public Slice<BoardEntityDto> findOrderByCreateDate(Integer page) {
        List<BoardEntityDto> boardList =
                boardRepository.findOrderByCreateDate(page);
        return toSlice(boardList, page);
    }

    public Slice<BoardEntityDto> findOrderByLike(Integer page) {
        List<BoardEntityDto> boardList = boardRepository.findOrderByPoint(page);
        return toSlice(boardList, page);
    }
}
