package com.team.twodari.platform.service;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.service.BoardSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class PlatformService {

    private final BoardSearchService boardSearchService;

    public Slice<BoardEntityDto> getMainPageData(int page, int condition) {
        Slice<BoardEntityDto> invalidCondition = switch (condition) {
            case 0 -> boardSearchService.findOrderByCreateDate(page);
            case 1 -> boardSearchService.findOrderByLike(page);
            default -> throw new IllegalArgumentException("Invalid condition");
        };
        return invalidCondition;
    }

}
