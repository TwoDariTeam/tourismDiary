package com.team.twodari.image.controller;

import com.team.twodari.image.service.SubBoardImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class SubBoardImageController {
    private final SubBoardImageService subBoardImageService;

    public SubBoardImageController(SubBoardImageService subBoardImageService) {
        this.subBoardImageService = subBoardImageService;
    }

    @PostMapping("/upload/subBoard/{subBoardSeq}")
    public ResponseEntity<String> uploadSubBoardImage(@RequestParam("file") MultipartFile file,
                                                      @PathVariable Long subBoardSeq) {
        try {
            String imageUrl = subBoardImageService.uploadSubBoardImage(file, subBoardSeq);

            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 실패");
        }
    }

    @DeleteMapping("/subBoard/{subBoardSeq}/delete/{imageSeq}")
    public ResponseEntity<Void> deleteSubBoardImage(@PathVariable Long subBoardSeq,
                                                    @PathVariable Long imageSeq) {
        subBoardImageService.deleteSubBoardImage(subBoardSeq, imageSeq);
        return ResponseEntity.ok().build();
    }

}
