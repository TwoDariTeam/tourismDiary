package com.team.twodari.subBoard.controller;

import com.team.twodari.subBoard.dto.SubBoardReadDto;
import com.team.twodari.subBoard.dto.SubBoardCreateDto;
import com.team.twodari.subBoard.dto.SubBoardUpdateDto;
import com.team.twodari.subBoard.entity.SubBoardEntity;
import com.team.twodari.subBoard.service.SubBoardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/{boardSeq}/subBoard")
public class SubBoardController {
    private final SubBoardService subBoardService;

    public SubBoardController(SubBoardService subBoardService) {
        this.subBoardService = subBoardService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSubBoard(@PathVariable("boardSeq") Long boardSeq,
                                                 @RequestBody @Valid SubBoardCreateDto dto) {
        Long createSubBoardSeq = subBoardService.createSubBoard(boardSeq, dto);

        if (createSubBoardSeq != null) {
            return ResponseEntity.ok("서브보드 작성 성공" + createSubBoardSeq);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서브보드 작성 실패");
    }

    @GetMapping("/{subBoardSeq}")
    public ResponseEntity<SubBoardReadDto> getSubBoardBySeq(@PathVariable Long subBoardSeq) {
        SubBoardEntity subBoard = subBoardService.getSubBoard(subBoardSeq);

        if (subBoard == null) return ResponseEntity.notFound().build();

        SubBoardReadDto boardReadDto = SubBoardReadDto.fromEntity(subBoard);
        return ResponseEntity.ok(boardReadDto);
    }

    @PutMapping("/{subBoardSeq}")
    public ResponseEntity<String> updateSubBoard(@PathVariable("boardSeq") Long boardSeq,
                                                 @PathVariable("subBoardSeq") Long subBoardSeq,
                                                 @RequestBody @Valid SubBoardUpdateDto dto) {
        Long updatedSubBoardSeq = subBoardService.updateSubBoard(boardSeq, subBoardSeq, dto);

        if (updatedSubBoardSeq != null) {
            return ResponseEntity.ok("서브보드 수정 성공" + updatedSubBoardSeq);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서브보드 수정 실패");
    }

    @DeleteMapping("/{subBoardSeq}")
    public ResponseEntity<String> deleteSubBoard(@PathVariable("boardSeq") Long boardSeq,
                                                 @PathVariable("subBoardSeq") Long subBoardSeq) {
        subBoardService.deleteSubBoard(boardSeq, subBoardSeq);

        return ResponseEntity.ok("서브보드 삭제 성공" + subBoardSeq);
    }
}
