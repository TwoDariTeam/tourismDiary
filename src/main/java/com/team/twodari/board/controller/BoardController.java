package com.team.twodari.board.controller;

import com.team.twodari.board.dto.BoardCreateDto;
import com.team.twodari.board.dto.BoardReadDto;
import com.team.twodari.board.dto.BoardUpdateDto;
import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<String> createBoard(@RequestBody @Valid BoardCreateDto dto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        Long createBoardSeq = boardService.createBoard(dto);

        if (createBoardSeq != null) {
            return ResponseEntity.ok("게시글 작성 성공" + createBoardSeq);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 작성 실패");
    }

    @GetMapping("/{boardSeq}")
    public ResponseEntity<BoardReadDto> getBoardBySeq(@PathVariable Long boardSeq) {
        BoardEntity board = boardService.getBoardBySeq(boardSeq);

        if (board == null) return ResponseEntity.notFound().build();

        BoardReadDto boardReadDto = BoardReadDto.fromEntity(board);
        return ResponseEntity.ok(boardReadDto);
    }

    @PutMapping("/{boardSeq}")
    public ResponseEntity<String> updateBoard(@PathVariable("boardSeq") Long boardSeq,
                                              @RequestBody @Valid BoardUpdateDto dto,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        // 현재 로그인한 유저의 닉네임
        String logInUserNickname = userDetails.getUsername();

        Long updateBoardSeq = boardService.updateBoard(boardSeq, dto, logInUserNickname);

        if (updateBoardSeq != null) {
            return ResponseEntity.ok("게시글 수정 성공" + updateBoardSeq);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 수정 실패");
    }

    @DeleteMapping("/{boardSeq}")
    public ResponseEntity<String> deleteBoard(@PathVariable("boardSeq") Long boardSeq,
                                              @AuthenticationPrincipal UserDetails userDetails) {

        String logInUserNickname = userDetails.getUsername();

        boardService.deleteBoard(boardSeq, logInUserNickname);

        return ResponseEntity.ok("게시글 삭제 성공");
    }
}
