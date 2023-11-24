package com.team.twodari.subBoard.service;

import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.board.repository.BoardRepository;
import com.team.twodari.subBoard.dto.SubBoardCreateDto;
import com.team.twodari.subBoard.dto.SubBoardUpdateDto;
import com.team.twodari.subBoard.entity.SubBoardEntity;
import com.team.twodari.subBoard.repository.SubBoardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class SubBoardService {
    private final BoardRepository boardRepository;
    private final SubBoardRepository subBoardRepository;

    public SubBoardService(BoardRepository boardRepository, SubBoardRepository subBoardRepository) {
        this.boardRepository = boardRepository;
        this.subBoardRepository = subBoardRepository;
    }

    public Long createSubBoard(Long boardSeq, SubBoardCreateDto createDto) {
        // 해당 보드가 존재하는지 확인
        BoardEntity board = boardRepository.findById(boardSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "보드가 존재하지 않습니다"));

        SubBoardEntity subBoard = SubBoardEntity.builder()
                .board(board)
                .contents(createDto.getContents())
                .build();
        subBoardRepository.save(subBoard);

        return subBoard.getSubBoardSeq();
    }

    public Long updateSubBoard(Long boardSeq, Long subBoardSeq, SubBoardUpdateDto updateDto) {
        if (boardSeq != null && !boardRepository.existsById(boardSeq)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "보드가 존재하지 않습니다");
        }

        SubBoardEntity subBoard = subBoardRepository.findById(subBoardSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "서브보드가 존재하지 않습니다"));

        subBoard.updateEntity(updateDto.getContents());
        subBoardRepository.save(subBoard);

        return subBoard.getSubBoardSeq();
    }

    public void deleteSubBoard(Long boardSeq, Long subBoardSeq) {
        if (boardSeq != null && !boardRepository.existsById(boardSeq)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "보드가 존재하지 않습니다");
        }

        SubBoardEntity subBoard = subBoardRepository.findById(subBoardSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "서브보드가 존재하지 않습니다"));

        subBoardRepository.delete(subBoard);
    }
}
