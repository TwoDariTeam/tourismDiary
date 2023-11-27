package com.team.twodari.board.repository;

import static com.team.twodari.board.entity.BoardLocation.*;
import static com.team.twodari.board.entity.QBoardEntity.*;
import static com.team.twodari.point.entity.QPointEntity.*;
import static com.team.twodari.tag.entity.QTagEntity.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.twodari.board.dto.BoardEntityDto;
import com.team.twodari.board.dto.QBoardEntityDto;
import com.team.twodari.board.entity.BoardLocation;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardSearchRepositoryImpl implements BoardSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<BoardEntityDto> findContains(Integer offset, Integer size, BoardLocation location, String word) {

		return createBaseQuery()
			.where(isContainsWord(word), isEqualLocation(location))
			.orderBy(orderByTotalPoint(), orderByCreateTime())
			.offset(offset)
			.limit(size + 1)
			.fetch();

	}

	@Override
	public List<BoardEntityDto> findOrderByCreateDate(Long offset, Integer size, BoardLocation location) {
		return createBaseQuery()
			.where(isEqualLocation(location))
			.orderBy(orderByCreateTime())
			.offset(offset)
			.limit(size + 1)
			.fetch();
	}

	@Override
	public List<BoardEntityDto> findOrderByPoint(
		Long offset,
		Integer size,
		BoardLocation location) {
		return createBaseQuery()
			.where(isEqualLocation(location))
			.orderBy(orderByTotalPoint())
			.offset(offset)
			.limit(size + 1)
			.fetch();

	}

	private JPAQuery<BoardEntityDto> createBaseQuery() {
		return jpaQueryFactory
			.select(
				new QBoardEntityDto(boardEntity.boardSeq,
					boardEntity.categorySeq,
					boardEntity.author,
					boardEntity.title,
					pointEntity.point.sum(),
					boardEntity.createTime,
					boardEntity.location)
			)
			.from(boardEntity)
			.leftJoin(pointEntity).on(isEqualToPointBoardSeq())
			.leftJoin(tagEntity).on(isEqualToTagBoardSeq())
			.groupBy(boardEntity.boardSeq);
	}

	private BooleanExpression isEqualToPointBoardSeq() {
		return boardEntity.boardSeq.eq(pointEntity.boardSeq);
	}

	private BooleanExpression isEqualToTagBoardSeq() {
		return boardEntity.boardSeq.eq(Expressions.numberTemplate(Long.class, "{0}", tagEntity.boardSeq));
	}

	private BooleanExpression isEqualLocation(BoardLocation location) {
		return location == EMPTY ? null : boardEntity.location.eq(location);
	}

	private OrderSpecifier<Integer> orderByTotalPoint() {
		return pointEntity.point.sum().desc();
	}

	private OrderSpecifier<LocalDateTime> orderByCreateTime() {
		return boardEntity.createTime.desc();
	}

	private BooleanExpression isContainsWord(String word) {
		return boardEntity.title.contains(word);
	}

}
