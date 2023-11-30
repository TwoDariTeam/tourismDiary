package com.team.twodari.image.service;

import com.team.twodari.common.service.S3UploadService;
import com.team.twodari.image.entity.UserImageEntity;
import com.team.twodari.image.repository.UserImageRepository;
import com.team.twodari.user.entity.UserEntity;
import com.team.twodari.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserImageService {
    private final UserImageRepository userImageRepository;
    private final S3UploadService s3UploadService;
    private final UserRepository userRepository;

    public String uploadUserImage(MultipartFile file, Long userSeq) throws IOException {
        UserEntity user = userRepository.findById(userSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다"));

        String imageUrl = s3UploadService.upload(file, "user_images");

        UserImageEntity image = UserImageEntity.builder()
                .user(user)
                .path(imageUrl)
                .build();

        userImageRepository.save(image);
        return imageUrl;
    }

    public void deleteUserImage(Long imageSeq) {
        UserImageEntity image = userImageRepository.findById(imageSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이미지가 존재하지 않습니다"));

        userImageRepository.delete(image);
    }
}
