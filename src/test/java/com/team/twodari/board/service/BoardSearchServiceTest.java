package com.team.twodari.board.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.board.entity.BoardLocation;
import com.team.twodari.board.repository.BoardRepository;
import com.team.twodari.point.entity.PointEntity;
import com.team.twodari.point.repository.PointRepository;

@Transactional
@SpringBootTest
class BoardSearchServiceTest {

	@Autowired
	private PointRepository pointRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardSearchService boardSearchService;

	@Nested
	@DisplayName("날짜 순으로 요청")
	class findByDate {

		@DisplayName("메인 페이지 날짜 순으로 요청을 하면 지역 상관 없이 최신 게시판 부터 가져온다")
		@Test
		void findOrderByCreateDate() {
			//given
			saveBoardEntity(9, BoardLocation.SEOUL);
			PageRequest pageRequest = PageRequest.of(0, 10);

			//when
			Slice<BoardEntityDto> result = boardSearchService.findOrderByCreateDate(pageRequest);

			//then
			assertThat(result.isFirst()).isTrue();
			List<BoardEntityDto> content = result.getContent();

			assertThat(content).hasSize(9);
			assertThat(content).extracting(BoardEntityDto::getBoardSeq)
							   .isSortedAccordingTo(Comparator.reverseOrder());
		}

		@DisplayName("지역과 함께 최신 순으로 요청하면 지역에 맞는 데이터를 최신순으로 가져온다")
		@Test
		void findByLocationOrderByDate() {
			//given
			saveBoardEntity(9, BoardLocation.SEOUL);
			saveBoardEntity(1, BoardLocation.JEJU);

			PageRequest pageRequest = PageRequest.of(0, 10);

			//when
			Slice<BoardEntityDto> result = boardSearchService.findOrderByCreateDateWithLocation(pageRequest, "SEOUL");

			//then
			assertThat(result).isInstanceOf(Slice.class);
			assertThat(result.isFirst()).isTrue();
			assertThat(result.hasNext()).isFalse();
			assertThat(result.hasPrevious()).isFalse();

			List<BoardEntityDto> content = result.getContent();
			assertThat(content).hasSize(9);
			assertThat(content).extracting(BoardEntityDto::getTotalPoint)
							   .isSortedAccordingTo(Comparator.reverseOrder());
		}
	}

	@Nested
	class findByPoint {
		@DisplayName("메인 페이지 포인트 순으로 요청을 하면 지역 상관 없이 인기 게시판 부터 가져온다")
		@Test
		void findOrderByPoint() {
			//given
			saveBoardEntity(14, BoardLocation.SEOUL);

			PageRequest pageRequest = PageRequest.of(1, 10);

			//when
			Slice<BoardEntityDto> result = boardSearchService.findOrderByPoint(pageRequest);

			//then
			assertThat(result).isInstanceOf(Slice.class);
			assertThat(result.isFirst()).isFalse();
			assertThat(result.hasNext()).isFalse();
			assertThat(result.hasPrevious()).isTrue();
			List<BoardEntityDto> content = result.getContent();

			assertThat(content).hasSize(4);
			assertThat(content).extracting(BoardEntityDto::getTotalPoint)
							   .isSortedAccordingTo(Comparator.reverseOrder());
		}

		@DisplayName("지역과 함께 point 순으로 요청하면 지역에 맞는 데이터를 point 순으로 가져온다")
		@Test
		void findByLocationOrderByPoint() {
			//given
			saveBoardEntity(12, BoardLocation.SEOUL);
			saveBoardEntity(2, BoardLocation.JEJU);

			PageRequest pageRequest = PageRequest.of(1, 10);

			//when
			Slice<BoardEntityDto> result = boardSearchService.findOrderByCreateDateWithLocation(pageRequest, "SEOUL");

			//then
			List<BoardEntityDto> content = result.getContent();
			assertThat(content).hasSize(2);
			assertThat(content).extracting(BoardEntityDto::getTotalPoint)
							   .isSortedAccordingTo(Comparator.reverseOrder());
		}
	}

	private void saveBoardEntity(int size, BoardLocation location) {
		for (int i = 0; i < size; i++) {
			BoardEntity boardEntity =
				boardRepository.save(BoardEntity.builder()
												.location(location)
												.build());

			pointRepository.save(PointEntity.builder()
											.boardSeq(boardEntity.getBoardSeq())
											.point(i)
											.build());
		}

	}
}