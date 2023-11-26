package com.team.twodari.image.service;

import com.team.twodari.image.entity.ImageEntity;
import com.team.twodari.image.repository.ImageRepository;
import com.team.twodari.subBoard.entity.SubBoardEntity;
import com.team.twodari.subBoard.repository.SubBoardRepository;
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
public class ImageService {
    private final ImageRepository imageRepository;
    private final S3UploadService s3UploadService;
    private final SubBoardRepository subBoardRepository;

    public String uploadImage(MultipartFile file, Long subBoardSeq) throws IOException {
        SubBoardEntity subBoard = subBoardRepository.findById(subBoardSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "서브보드가 존재하지 않습니다"));

        // 이미지를 S3에 업로드하고 S3에 저장된 URL을 반환
        String imageUrl = s3UploadService.upload(file);

        ImageEntity image = ImageEntity.builder()
                .subBoard(subBoard)
                .path(imageUrl)
                .build();

        imageRepository.save(image);
        return imageUrl;
    }

    public void deleteImage(Long subBoardSeq, Long imageSeq) {
        if (isExistSubBoard(subBoardSeq)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "서브보드가 존재하지 않습니다");
        }

        ImageEntity image = imageRepository.findById(imageSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이미지가 존재하지 않습니다"));
        image.deleteEntity();

        imageRepository.save(image);
    }

    private boolean isExistSubBoard(Long subBoardSeq) {
        return subBoardSeq != null && !subBoardRepository.existsById(subBoardSeq);
    }

}
