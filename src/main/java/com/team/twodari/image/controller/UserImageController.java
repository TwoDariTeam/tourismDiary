package com.team.twodari.image.controller;

import com.team.twodari.image.service.UserImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class UserImageController {
    private final UserImageService userImageService;

    public UserImageController(UserImageService userImageService) {
        this.userImageService = userImageService;
    }

    @PostMapping("/upload/user/{userSeq}")
    public ResponseEntity<String> uploadUserImage(@RequestParam("file") MultipartFile file,
                                                  @PathVariable Long userSeq) {
        try {
            String imageUrl = userImageService.uploadUserImage(file, userSeq);

            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 실패");
        }
    }

    @DeleteMapping("/user/{userSeq}/delete/{imageSeq}")
    public ResponseEntity<Void> deleteUserImage(@PathVariable Long userSeq,
                                                @PathVariable Long imageSeq) {
        userImageService.deleteUserImage(userSeq, imageSeq);
        return ResponseEntity.ok().build();
    }
}
