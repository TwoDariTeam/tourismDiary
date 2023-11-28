package com.team.twodari.board.controller.facade;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.service.BoardMyPageService;
import com.team.twodari.common.dto.PageResponse;
import com.team.twodari.tag.service.TagService;
import com.team.twodari.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFacadeService {

    private final BoardMyPageService boardService;
    private final TagService tagService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public PageResponse<List<BoardOwnResponse>> findOwnBoardOrderByCreateDate(String email, Integer page){
//        UserEntity userEntity = userService.findByEmail(email);

        Page<BoardOwnResponse> ownBoardResponse = boardService.findOwnBoardOrderByCreateDate(email, page);

        for(BoardOwnResponse response : ownBoardResponse.getContent()){
            List<String> tagNames =
                    tagService.findTagNamesByBoardSeq(response.getBoardSeq());

            response.connectTags(tagNames);
        }

        return PageResponse.of(ownBoardResponse.getContent(), ownBoardResponse);
    }

    @Transactional(readOnly = true)
    public PageResponse<List<BoardMyLikedResponse>> findMyLikedBoardOrderByCreateDate(String email, Integer page){
//        UserEntity userEntity = userService.findByEmail(email);
        Page<BoardMyLikedResponse> myLikedBoardResponse = boardService.findMyLikedBoardOrderByCreateDate(email, page);

        for(BoardMyLikedResponse response : myLikedBoardResponse.getContent()){
            List<String> tagNames =
                    tagService.findTagNamesByBoardSeq(response.getBoardSeq());

            response.connectTags(tagNames);
        }

        return PageResponse.of(myLikedBoardResponse.getContent(), myLikedBoardResponse);
    }
}
