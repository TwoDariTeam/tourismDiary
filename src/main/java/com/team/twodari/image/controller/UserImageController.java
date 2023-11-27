package com.team.twodari.image.controller;

import com.team.twodari.common.service.S3UploadService;
import com.team.twodari.image.repository.UserImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserImageController {
    private final UserImageRepository userImageRepository;
    private final S3UploadService s3UploadService;
}
