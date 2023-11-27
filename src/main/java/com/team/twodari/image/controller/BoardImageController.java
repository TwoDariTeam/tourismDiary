package com.team.twodari.image.controller;

import com.team.twodari.image.service.BoardImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class BoardImageController {
    private final BoardImageService boardImageService;

    public BoardImageController(BoardImageService boardImageService) {
        this.boardImageService = boardImageService;
    }

    @PostMapping("/upload/board/{boardSeq}")
    public ResponseEntity<String> uploadBoardImage(@RequestParam("file") MultipartFile file,
                                                   @PathVariable Long boardSeq) {
        try {
            String imageUrl = boardImageService.uploadBoardImage(file, boardSeq);

            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 실패");
        }
    }

    @DeleteMapping("/board/{boardSeq}/delete/{imageSeq}")
    public ResponseEntity<Void> deleteBoardImage(@PathVariable Long boardSeq,
                                                 @PathVariable Long imageSeq) {
        boardImageService.deleteBoardImage(boardSeq, imageSeq);
        return ResponseEntity.ok().build();
    }
}
