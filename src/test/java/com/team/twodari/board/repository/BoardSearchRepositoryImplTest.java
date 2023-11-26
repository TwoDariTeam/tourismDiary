package com.team.twodari.board.repository;

import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.config.DataConfig;
import com.team.twodari.point.entity.PointEntity;
import com.team.twodari.point.repository.PointRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

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


    @Disabled
    @DisplayName("createDate order by - datetime이라서 아래 코드는 불완전")
    @Test
    void checkCreateDate() {
        var boardList = boardRepository.findOrderByCreateDate(0L,10);

        assertThat(boardList).size().isEqualTo(10);
    }

    @DisplayName("board에서 제목에 1이 들어간 board들을 찾아온다")
    @Test
    void findContains() {
        var boardList = boardRepository.findContains(0, 10,"1");

        var board1 = boardList.get(0);

        assertThat(boardList).size().isEqualTo(3);
        assertThat(board1.getTitle()).contains("1");
    }

    @DisplayName("totalPoint 순으로 board를 찾아온다.")
    @Test
    void findOrderByPoint() {
        var boardList = boardRepository.findOrderByPoint(0L,10,0,"type");

        assertThat(boardList.size()).isEqualTo(11);
        assertThat(boardList).isSortedAccordingTo((a, b) ->
                b.getTotalPoint() - a.getTotalPoint()
        );
    }
}