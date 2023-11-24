package com.team.twodari.board.repository;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.config.DataConfig;
import com.team.twodari.point.entity.PointEntity;
import com.team.twodari.point.repository.PointRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Slice;

import static org.assertj.core.api.Assertions.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(DataConfig.class)
@DataJpaTest
class BoardSearchRepositoryImplTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PointRepository pointRepository;

    @BeforeAll
    void setUp() {
        for (int i = 0; i < 12; i++) {
            BoardEntity boardEntity = boardRepository.save(BoardEntity.builder()
                    .author("author" + i)
                    .title("title" + i)
                    .build());

            pointRepository.save(PointEntity.builder()
                    .boardSeq(boardEntity.getBoardSeq())
                    .point(i)
                    .build());

        }
    }


    @DisplayName("createDate order by")
    @Test
    void checkCreateDate() {
        Slice<BoardEntityDto> boardList = boardRepository.findOrderByCreateDate(0);

        assertThat(boardList).size().isEqualTo(10);
        assertThat(boardList.hasNext()).isTrue();
        assertThat(boardList.hasPrevious()).isFalse();
    }

    @DisplayName("findContains")
    @Test
    void findContains() {
        var boardList = boardRepository.findContains(0, "1");

        var board1 = boardList.getContent().get(0);

        assertThat(boardList).size().isEqualTo(3);
        assertThat(board1.getTitle()).contains("1");
    }

    @DisplayName("findOrderByPoint")
    @Test
    void findOrderByPoint() {
        var boardList = boardRepository.findOrderByPoint(0);

        assertThat(boardList.getContent().size()).isEqualTo(10);
        assertThat(boardList.getContent()).isSortedAccordingTo((a, b) ->
                b.getTotalPoint() - a.getTotalPoint()
        );
    }
}