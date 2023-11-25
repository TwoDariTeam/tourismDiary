package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.entity.BoardEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BoardMyPageRepositoryTest {

    @Autowired
    protected BoardRepository boardRepository;

    @Nested
    @DisplayName("BoardMyPageRepository 내부 함수")
    class BoardMyPageRepository {

        @Test
        @DisplayName("글쓴이가 nickname인 게시글 목록 조회")
        void findOwnBoardOrderByCreateDate() {
            //given
            Long boardSeq = 1L;
            Long categorySeq = 1L;
            String nickname = "테스트 닉네임";
            String title = "테스트 제목";
            Integer accessRole = 1;
            String deleted = null;

            BoardEntity entity = BoardEntity.builder()
                    .boardSeq(boardSeq)
                    .categorySeq(categorySeq)
                    .author(nickname)
                    .title(title)
                    .accessRole(accessRole)
                    .deleted(deleted)
                    .build();
            BoardEntity savedEntity = boardRepository.save(entity);

            //when
            List<BoardOwnResponse> response = boardRepository.findOwnBoardOrderByCreateDate(nickname, 0);

            //then
            Assertions.assertThat(response.size()).isEqualTo(1);
            Assertions.assertThat(response.get(0).getAuthor()).isEqualTo(nickname);
            Assertions.assertThat(response.get(0).getTitle()).isEqualTo(title);
        }


        @Test
        @DisplayName("내가 좋아요 누른 게시글 목록 조회")
        void findMyLikedBoardOrderByCreateDate() {

        }
    }
}