package com.team.twodari.board.repository;

import static com.team.twodari.board.entity.BoardLocation.*;
import static com.team.twodari.board.entity.QBoardEntity.*;
import static com.team.twodari.image.entity.QBoardImageEntity.*;
import static com.team.twodari.point.entity.QPointEntity.*;
import static com.team.twodari.tag.entity.QTagEntity.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.twodari.board.entity.BoardLocation;
import com.team.twodari.platform.dto.BoardContainsStringDto;
import com.team.twodari.platform.dto.BoardDateDto;
import com.team.twodari.platform.dto.BoardPointDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardSearchRepositoryImpl implements BoardSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<BoardContainsStringDto> findContains(Integer offset, Integer size, BoardLocation location, String word) {

		return jpaQueryFactory
			.select(
				Projections.constructor(BoardContainsStringDto.class,
					boardEntity.boardSeq,
					boardEntity.categorySeq,
					boardEntity.user.nickname,
					boardEntity.title,
					pointEntity.point.sum(),
					boardEntity.introduce,
					boardImageEntity.path,
					boardEntity.createTime,
					boardEntity.location)
			)
			.from(boardEntity)
			.leftJoin(pointEntity).on(isEqualToPointBoardSeq())
			.leftJoin(tagEntity).on(isEqualToTagBoardSeq())
			.leftJoin(boardImageEntity).on(isEqualToImageBoardSeq()).fetchJoin()
			.groupBy(boardEntity.boardSeq)
			.where(isContainsWord(word), isEqualLocation(location))
			.orderBy(orderByTotalPoint(), orderByCreateTime())
			.offset(offset)
			.limit(size + 1)
			.fetch();

	}

	@Override
	public List<BoardDateDto> findOrderByCreateDate(Long offset, Integer size, BoardLocation location) {
		return jpaQueryFactory
			.select(
				Projections.constructor(BoardDateDto.class,
					boardEntity.boardSeq,
					boardEntity.categorySeq,
					boardEntity.user.nickname,
					boardEntity.title,
					pointEntity.point.sum(),
					boardEntity.introduce,
					boardImageEntity.path,
					boardEntity.createTime,
					boardEntity.location
				)
			)
			.from(boardEntity)
			.leftJoin(pointEntity).on(isEqualToPointBoardSeq())
			.leftJoin(tagEntity).on(isEqualToTagBoardSeq())
			.leftJoin(boardImageEntity).on(isEqualToImageBoardSeq()).fetchJoin()
			.groupBy(boardEntity.boardSeq)
			.where(isEqualLocation(location))
			.orderBy(orderByCreateTime())
			.offset(offset)
			.limit(size + 1)
			.fetch();
	}

	@Override
	public List<BoardPointDto> findOrderByPoint(
		Long offset,
		Integer size,
		BoardLocation location) {
		return jpaQueryFactory
			.select(
				Projections.constructor(BoardPointDto.class,
					boardEntity.boardSeq,
					boardEntity.categorySeq,
					boardEntity.user.nickname,
					boardEntity.title,
					pointEntity.point.sum(),
					boardEntity.introduce,
					boardImageEntity.path,
					boardEntity.createTime,
					boardEntity.location)
			)
			.from(boardEntity)
			.leftJoin(pointEntity).on(isEqualToPointBoardSeq())
			.leftJoin(tagEntity).on(isEqualToTagBoardSeq())
			.leftJoin(boardImageEntity).on(isEqualToImageBoardSeq()).fetchJoin()
			.groupBy(boardEntity.boardSeq)
			.where(isEqualLocation(location))
			.orderBy(orderByTotalPoint())
			.offset(offset)
			.limit(size + 1)
			.fetch();

	}

	private BooleanExpression isEqualToPointBoardSeq() {
		return boardEntity.boardSeq.eq(pointEntity.boardSeq);
	}

	private BooleanExpression isEqualToTagBoardSeq() {
		return boardEntity.boardSeq.eq(Expressions.numberTemplate(Long.class, "{0}", tagEntity.boardSeq));
	}

	private BooleanExpression isEqualToImageBoardSeq() {
		return boardEntity.boardSeq.eq(boardImageEntity.boardImageSeq);
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
