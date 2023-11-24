package com.team.twodari.board.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.twodari.board.dto.BoardEntityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.team.twodari.board.entity.QBoardEntity.boardEntity;
import static com.team.twodari.common.constant.Constant.PAGE_SIZE;
import static com.team.twodari.point.entity.QPointEntity.pointEntity;
import static com.team.twodari.tag.entity.QTagEntity.tagEntity;

@RequiredArgsConstructor
@Repository
public class BoardSearchRepositoryImpl implements BoardSearchRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardEntityDto> findContains(Integer page, String word) {

        return createBaseQuery()
                .where(isContainsWord(word))
                .orderBy(orderByTotalPoint(), orderByCreateTime())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
                .fetch();

    }

    @Override
    public List<BoardEntityDto> findOrderByCreateDate(Integer page) {
        return createBaseQuery()
                .orderBy(orderByCreateTime())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
                .fetch();
    }

    @Override
    public List<BoardEntityDto> findOrderByPoint(Integer page) {
        return createBaseQuery()
                .orderBy(orderByTotalPoint())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
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
