package com.team.twodari.board.controller;

import com.team.twodari.board.dto.BoardCreateDto;
import com.team.twodari.board.dto.BoardUpdateDto;
import com.team.twodari.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBoard(@RequestBody BoardCreateDto dto) {
        Long createBoardSeq = boardService.createBoard(dto);

        if (createBoardSeq != null) {
            return ResponseEntity.ok("게시글 작성 성공" + createBoardSeq);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 작성 실패");
        }
    }

    @PutMapping("/{boardSeq}")
    public ResponseEntity<String> updateBoard(@PathVariable("boardSeq") Long boardSeq,
                                              @RequestBody BoardUpdateDto dto) {
        Long updateBoardSeq = boardService.updateBoard(boardSeq, dto);

        if (updateBoardSeq != null) {
            return ResponseEntity.ok("게시글 수정 성공" + updateBoardSeq);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 수정 실패");
        }
    }

    @DeleteMapping("/{boardSeq}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardSeq") Long boardSeq) {
        boardService.deleteBoard(boardSeq);

        return ResponseEntity.ok("게시글 삭제 성공");
    }
}
