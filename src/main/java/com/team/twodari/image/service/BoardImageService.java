package com.team.twodari.image.service;

import com.team.twodari.board.repository.BoardRepository;
import com.team.twodari.common.service.S3UploadService;
import com.team.twodari.image.entity.BoardImageEntity;
import com.team.twodari.image.repository.BoardImageRepository;
import com.team.twodari.board.entity.BoardEntity;
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
public class BoardImageService {
    private final BoardImageRepository boardImageRepository;
    private final S3UploadService s3UploadService;
    private final BoardRepository boardRepository;

    public String uploadBoardImage(MultipartFile file, Long boardSeq) throws IOException {
        BoardEntity board = boardRepository.findById(boardSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "보드가 존재하지 않습니다"));

        String imageUrl = s3UploadService.upload(file);

        BoardImageEntity image = BoardImageEntity.builder()
                .board(board)
                .path(imageUrl)
                .build();

        boardImageRepository.save(image);
        return imageUrl;
    }

    public void deleteBoardImage(Long boardSeq, Long imageSeq) {
        if (isExistBoard(boardSeq)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "보드가 존재하지 않습니다");
        }

        BoardImageEntity image = boardImageRepository.findById(imageSeq)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이미지가 존재하지 않습니다"));

        boardImageRepository.delete(image);
    }

    private boolean isExistBoard(Long boardSeq) {
        return boardSeq != null && !boardRepository.existsById(boardSeq);
    }
}
