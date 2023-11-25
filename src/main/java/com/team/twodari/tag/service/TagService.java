package com.team.twodari.tag.service;

import com.team.twodari.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<String> findTagNamesByBoardSeq(Long boardSeq){
        return tagRepository.findTagNamesByBoardSeq(boardSeq);
    }
}
