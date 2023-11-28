package com.team.twodari.board.controller;

import com.team.twodari.base.BaseControllerTest;
import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.support.BoardFixture;
import com.team.twodari.common.dto.PageResponse;
import com.team.twodari.global.util.SliceConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.team.twodari.common.constant.Constant.PAGE_SIZE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
class BoardMyPageControllerTest extends BaseControllerTest {
    private static Long seq = 1L;

    @Nested
    @DisplayName("내가 쓴 글 목록 조회 최신순 API 호출")
    class FindOwnBoardOrderByCreateDateTest {

        @Test
        @DisplayName("성공")
        void findOwnBoardOrderByCreateDate() throws Exception {

            // Given
            Integer page = 0;
            Integer pageSize = 10;
            String path = BoardFixture.getImageUrl();

            BoardOwnResponse boardOwnResponse1 = BoardFixture.findOwnBoardOrderByCreateDate(seq++);
            BoardOwnResponse boardOwnResponse2 = BoardFixture.findOwnBoardOrderByCreateDate(seq++);

            List<BoardOwnResponse> boardOwnResponses = List.of(boardOwnResponse1, boardOwnResponse2);

            Page<BoardOwnResponse> responses = new PageImpl<>(boardOwnResponses, PageRequest.of(page, pageSize), boardOwnResponses.size());
            PageResponse response = PageResponse.of(responses.getContent(), responses);

            when(boardFacadeService.findOwnBoardOrderByCreateDate(any(), anyInt())).thenReturn(response);

            // When
            ResultActions resultActions = mockMvc.perform(
                    get("/board/mypage/own")
                            .param("page", String.valueOf(page))
                    .contentType(MediaType.APPLICATION_JSON)
            );

            // Then
            resultActions.andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("내가 좋아요한 글 목록 조회 최신순 API 호출")
    class FindMyLikedBoardOrderByCreateDate {

        @Test
        @DisplayName("성공")
        void findMyLikedBoardOrderByCreateDate() throws Exception {

            // Given
            Integer page = 0;
            Integer pageSize = 10;

            BoardMyLikedResponse boardMyLikedResponse1 = BoardFixture.findMyLikedBoardOrderByCreateDate(seq++);
            BoardMyLikedResponse boardMyLikedResponse2 = BoardFixture.findMyLikedBoardOrderByCreateDate(seq++);
            List<BoardMyLikedResponse> boardLikedResponses = List.of(boardMyLikedResponse1, boardMyLikedResponse2);
            Page<BoardMyLikedResponse> responses = new PageImpl<>(boardLikedResponses, PageRequest.of(page, pageSize), boardLikedResponses.size());
            PageResponse response = PageResponse.of(responses.getContent(), responses);

            when(boardFacadeService.findMyLikedBoardOrderByCreateDate(any(), anyInt())).thenReturn(response);

            // When
            ResultActions resultActions = mockMvc.perform(
                    get("/board/mypage/like")
                            .contentType(MediaType.APPLICATION_JSON)
            );

            // Then
            resultActions.andExpect(status().isOk());
        }
    }

}