package com.team.twodari.board.service;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.repository.BoardRepository;
import com.team.twodari.board.support.BoardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class BoardMyPageServiceTest {

    @InjectMocks
    private BoardMyPageService boardMyPageService;

    @Mock
    private BoardRepository boardRepository;

    @Nested
    @DisplayName("findOwnBoardOrderByCreateDate 메서드 실행 시")
    class FindOwnBoardOrderByCreateDate {

        @Test
        @DisplayName("성공")
        void success() {
            //given
            String nickname = "nickname";
            Integer page = 1;
            BoardOwnResponse data1 = BoardFixture.findOwnBoardOrderByCreateDate(1L);
            BoardOwnResponse data2 = BoardFixture.findOwnBoardOrderByCreateDate(2L);
            List<BoardOwnResponse> responses = List.of(data1, data2);

            given(boardRepository.findOwnBoardOrderByCreateDate(anyString(), anyInt()))
                    .willReturn(responses);

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
            BoardMyLikedResponse data1 = BoardFixture.findMyLikedBoardOrderByCreateDate(1L);
            BoardMyLikedResponse data2 = BoardFixture.findMyLikedBoardOrderByCreateDate(2L);
            List<BoardMyLikedResponse> responses = List.of(data1, data2);

            given(boardRepository.findMyLikedBoardOrderByCreateDate(anyString(), anyInt()))
                    .willReturn(responses);

            //when
            boardMyPageService.findMyLikedBoardOrderByCreateDate(nickname, page);

            //then
            verify(boardRepository).findMyLikedBoardOrderByCreateDate(nickname, page);
        }
    }

}