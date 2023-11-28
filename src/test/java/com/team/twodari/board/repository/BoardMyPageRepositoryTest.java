package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.board.support.BoardFixture;
import com.team.twodari.config.DataConfig;
import com.team.twodari.global.config.JpaAuditingConfig;
import com.team.twodari.image.repository.BoardImageRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;

import java.util.List;

@Import({DataConfig.class, JpaAuditingConfig.class})
@DataJpaTest
class BoardMyPageRepositoryTest {

    @Autowired
    protected BoardRepository boardRepository;
    @Autowired
    protected BoardImageRepository boardImageRepository;

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

            BoardEntity board = BoardEntity.builder()
                    .boardSeq(boardSeq)
                    .categorySeq(categorySeq)
                    .author(nickname)
                    .title(title)
                    .accessRole(accessRole)
                    .deleted(deleted)
                    .build();

            BoardEntity savedEntity = boardRepository.save(board);

            //when
            Page<BoardOwnResponse> response = boardRepository.findOwnBoardOrderByCreateDate(nickname, 0);

            //then
            Assertions.assertThat(response.getContent().size()).isEqualTo(1);
            Assertions.assertThat(response.getContent().get(0).getAuthor()).isEqualTo(nickname);
            Assertions.assertThat(response.getContent().get(0).getTitle()).isEqualTo(title);
            Assertions.assertThat(response.getContent().get(0).getWriteDate()).isNotNull();
        }


        @Test
        @DisplayName("내가 좋아요 누른 게시글 목록 조회")
        void findMyLikedBoardOrderByCreateDate() {

        }
    }
}