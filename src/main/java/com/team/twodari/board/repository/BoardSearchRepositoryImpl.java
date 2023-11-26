package com.team.twodari.board.repository;

import static com.team.twodari.board.entity.QBoardEntity.*;
import static com.team.twodari.point.entity.QPointEntity.*;
import static com.team.twodari.tag.entity.QTagEntity.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.twodari.board.dto.BoardEntityDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardSearchRepositoryImpl implements BoardSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<BoardEntityDto> findContains(Integer offset, Integer size, String word) {

		return createBaseQuery()
			.where(isContainsWord(word))
			.orderBy(orderByTotalPoint(), orderByCreateTime())
			.offset(offset)
			.limit(size + 1)
			.fetch();

	}

	@Override
	public List<BoardEntityDto> findOrderByCreateDate(Long offset, Integer size) {
		return createBaseQuery()
			.orderBy(orderByCreateTime())
			.offset(offset)
			.limit(size + 1)
			.fetch();
	}

	//TODO condition type에 따른 select 쿼리 작성
	@Override
	public List<BoardEntityDto> findOrderByPoint(Long offset, Integer size, Integer condition, String type) {
		return createBaseQuery()
			.orderBy(orderByTotalPoint())
			.offset(offset)
			.limit(size + 1)
			.fetch();

	}

	private JPAQuery<BoardEntityDto> createBaseQuery() {
		return jpaQueryFactory
			.select(Projections.constructor(
				BoardEntityDto.class,
				boardEntity.boardSeq,
				boardEntity.categorySeq,
				boardEntity.author,
				boardEntity.title,
				pointEntity.point.sum()
			))
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
