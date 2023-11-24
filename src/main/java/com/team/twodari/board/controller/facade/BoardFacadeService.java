package com.team.twodari.board.controller.facade;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.service.BoardMyPageService;
import com.team.twodari.global.util.SliceConverter;
import com.team.twodari.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFacadeService {

    private final BoardMyPageService boardService;
    private final TagService tagService;

    public Slice<BoardOwnResponse> findOwnBoardOrderByCreateDate(String nickname, Integer page){
        List<BoardOwnResponse> ownBoardResponse = boardService.findOwnBoardOrderByCreateDate(nickname, page);

        for(BoardOwnResponse response : ownBoardResponse){
            List<String> tagNames =
                    tagService.findTagNamesByBoardSeq(response.getBoardSeq());

            response.connectTags(tagNames);
        }

        return SliceConverter.toSlice(ownBoardResponse, page);
    }

    public Slice<BoardMyLikedResponse> findMyLikedBoardOrderByCreateDate(String nickname, Integer page){
        List<BoardMyLikedResponse> myLikedBoardResponse = boardService.findMyLikedBoardOrderByCreateDate(nickname, page);

        for(BoardMyLikedResponse response : myLikedBoardResponse){
            List<String> tagNames =
                    tagService.findTagNamesByBoardSeq(response.getBoardSeq());

            response.connectTags(tagNames);
        }

        return SliceConverter.toSlice(myLikedBoardResponse, page);
    }
}
