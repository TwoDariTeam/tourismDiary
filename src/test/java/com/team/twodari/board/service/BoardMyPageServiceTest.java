package com.team.twodari.board.service;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.repository.BoardRepository;
import com.team.twodari.board.support.BoardFixture;
import com.team.twodari.image.repository.BoardImageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class BoardMyPageServiceTest {

    @InjectMocks
    private BoardMyPageService boardMyPageService;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private BoardImageRepository boardImageRepository;

    @Nested
    @DisplayName("findOwnBoardOrderByCreateDate 메서드 실행 시")
    class FindOwnBoardOrderByCreateDate {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            String nickname = "nickname";
            Integer page = 1;
            Integer pageSize = 10;
            BoardOwnResponse data1 = BoardFixture.findOwnBoardOrderByCreateDate(1L);
            BoardOwnResponse data2 = BoardFixture.findOwnBoardOrderByCreateDate(2L);
            List<BoardOwnResponse> boardOwnResponses = List.of(data1, data2);
            Page<BoardOwnResponse> responses = new PageImpl<>(boardOwnResponses, PageRequest.of(page, pageSize), boardOwnResponses.size());
            List<String> imageUrls = List.of("https://besp1-2team-s3-bucket.s3.amazonaws.com/bda0087c-7969-4d9d-976e-6b0c0e564768-image.jpeg");

            given(boardRepository.findOwnBoardOrderByCreateDate(anyString(), anyInt()))
                    .willReturn(responses);
            given(boardImageRepository.findByBoardSeq(anyLong()))
                    .willReturn(imageUrls);

            //when
            boardMyPageService.findOwnBoardOrderByCreateDate(nickname, page);

            //then
            verify(boardRepository).findOwnBoardOrderByCreateDate(nickname, page);
        }
    }

    @Nested
    @DisplayName("findMyLikedBoardOrderByCreateDate 메서드 실행 시")
    class FindMyLikedBoardOrderByCreateDate {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            String nickname = "nickname";
            Integer page = 1;
            Integer pageSize = 10;
            BoardMyLikedResponse data1 = BoardFixture.findMyLikedBoardOrderByCreateDate(1L);
            BoardMyLikedResponse data2 = BoardFixture.findMyLikedBoardOrderByCreateDate(2L);
            List<BoardMyLikedResponse> boardMyLikedResponses = List.of(data1, data2);
            Page<BoardMyLikedResponse> responses = new PageImpl<>(boardMyLikedResponses, PageRequest.of(page, pageSize), boardMyLikedResponses.size());
            List<String> imageUrls = List.of("https://besp1-2team-s3-bucket.s3.amazonaws.com/bda0087c-7969-4d9d-976e-6b0c0e564768-image.jpeg");

            given(boardRepository.findMyLikedBoardOrderByCreateDate(anyString(), anyInt()))
                    .willReturn(responses);

            given(boardImageRepository.findByBoardSeq(anyLong()))
                    .willReturn(imageUrls);

            //when
            boardMyPageService.findMyLikedBoardOrderByCreateDate(nickname, page);

            //then
            verify(boardRepository).findMyLikedBoardOrderByCreateDate(nickname, page);
        }
    }

}