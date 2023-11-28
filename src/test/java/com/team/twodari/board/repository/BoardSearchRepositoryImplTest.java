package com.team.twodari.board.repository;

import static com.team.twodari.board.entity.BoardLocation.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.config.DataConfig;
import com.team.twodari.platform.dto.BoardDateDto;
import com.team.twodari.point.entity.PointEntity;
import com.team.twodari.point.repository.PointRepository;

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
			BoardEntity boardEntity =
				boardRepository.save(BoardEntity.builder()
												.author("author" + i)
												.location(SEOUL)
												.title("title" + i)
												.build());

			pointRepository.save(PointEntity.builder()
											.boardSeq(boardEntity.getBoardSeq())
											.point(i)
											.build());
		}
	}

	@Disabled("DateTime을 사용중이므로 해결 방법 필요")
	@DisplayName("최신 날짜 순으로 가져온다")
	@Test
	void checkCreateDate() {
		var boardList = boardRepository.findOrderByCreateDate(0L, 10, SEOUL);

		assertThat(boardList).size().isEqualTo(11);
		assertThat(boardList).extracting(BoardDateDto::getBoardSeq)
							 .isSortedAccordingTo(Comparator.reverseOrder());
	}

	@DisplayName("board에서 제목에 1이 들어간 board들을 찾아온다")
	@Test
	void findContains() {
		var boardList = boardRepository.findContains(0, 10, SEOUL, "1");

		var board1 = boardList.get(0);

		assertThat(boardList).size().isEqualTo(3);
		assertThat(board1.getTitle()).contains("1");
	}

	@DisplayName("totalPoint 순으로 board를 찾아온다.")
	@Test
	void findOrderByPoint() {
		var boardList = boardRepository.findOrderByPoint(0L, 10, SEOUL);

		assertThat(boardList.size()).isEqualTo(11);
		assertThat(boardList).isSortedAccordingTo((a, b) ->
			b.getTotalPoint() - a.getTotalPoint()
		);
	}

}