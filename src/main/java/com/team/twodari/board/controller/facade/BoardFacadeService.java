package com.team.twodari.board.controller.facade;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.service.BoardMyPageService;
import com.team.twodari.global.util.SliceConverter;
import com.team.twodari.tag.service.TagService;
import com.team.twodari.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
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
    public Slice<BoardOwnResponse> findOwnBoardOrderByCreateDate(String email, Integer page){
//        UserEntity userEntity = userService.findByEmail(email);

        List<BoardOwnResponse> ownBoardResponse = boardService.findOwnBoardOrderByCreateDate("nickname", page);

        for(BoardOwnResponse response : ownBoardResponse){
            List<String> tagNames =
                    tagService.findTagNamesByBoardSeq(response.getBoardSeq());

            response.connectTags(tagNames);
        }

        return SliceConverter.toSlice(ownBoardResponse, page);
    }

    @Transactional(readOnly = true)
    public Slice<BoardMyLikedResponse> findMyLikedBoardOrderByCreateDate(String email, Integer page){
//        UserEntity userEntity = userService.findByEmail(email);
        List<BoardMyLikedResponse> myLikedBoardResponse = boardService.findMyLikedBoardOrderByCreateDate("nickname", page);

        for(BoardMyLikedResponse response : myLikedBoardResponse){
            List<String> tagNames =
                    tagService.findTagNamesByBoardSeq(response.getBoardSeq());

            response.connectTags(tagNames);
        }

        return SliceConverter.toSlice(myLikedBoardResponse, page);
    }
}
