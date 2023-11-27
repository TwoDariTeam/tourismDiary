package com.team.twodari.board.service;

import com.team.twodari.board.dto.BoardCreateDto;
import com.team.twodari.board.dto.BoardUpdateDto;
import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.board.repository.BoardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Long createBoard(BoardCreateDto createDto) {
        BoardEntity board = BoardEntity.builder()
                .author(createDto.getAuthor())
                .title(createDto.getTitle())
                .accessRole(createDto.getAccessRole())
                .build();
        boardRepository.save(board);

        return board.getBoardSeq();
    }

    @Transactional(readOnly = true)
    public BoardEntity getBoardBySeq(Long boardSeq) {
        return boardRepository.findById(boardSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다"));
    }

    @Transactional
    public Long updateBoard(Long boardSeq, BoardUpdateDto updateDto) {
        BoardEntity board = boardRepository.findById(boardSeq)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다"));
        board.updateEntity(updateDto.getTitle(), updateDto.getAccessRole());
        boardRepository.save(board);

        return board.getBoardSeq();
    }

    @Transactional
    public void deleteBoard(Long boardSeq) {
        BoardEntity board = boardRepository.findById(boardSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다"));
        board.deleteEntity();

        boardRepository.save(board);
    }
}
