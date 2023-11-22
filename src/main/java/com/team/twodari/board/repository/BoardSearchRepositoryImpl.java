package com.team.twodari.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.twodari.board.dto.BoardEntityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.team.twodari.board.entity.QBoardEntity.boardEntity;
import static com.team.twodari.common.constant.Constant.PAGE_SIZE;
import static com.team.twodari.point.entity.QPointEntity.pointEntity;

@RequiredArgsConstructor
@Repository
public class BoardSearchRepositoryImpl implements BoardSearchRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<BoardEntityDto> findContains(Integer page, String word) {

        List<BoardEntityDto> boardEntities = jpaQueryFactory
                .select(Projections.constructor(
                        BoardEntityDto.class,
                        boardEntity.boardSeq,
                        boardEntity.categorySeq,
                        boardEntity.author,
                        boardEntity.title,
                        pointEntity.point.sum()
                ))
                .from(boardEntity)
                .leftJoin(pointEntity).on(isEqualBoardSeq())
                .where(isContainsWord(word))
                .groupBy(boardEntity.boardSeq)
                .orderBy(pointEntity.point.sum().desc())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
                .fetch();

        return toSlice(boardEntities);

    }

    @Override
    public Slice<BoardEntityDto> findOrderByCreateDate(Integer page) {
        List<BoardEntityDto> boardEntities = jpaQueryFactory
                .select(Projections.constructor(
                        BoardEntityDto.class,
                        boardEntity.boardSeq,
                        boardEntity.categorySeq,
                        boardEntity.author,
                        boardEntity.title,
                        pointEntity.point.sum()
                ))
                .from(boardEntity)
                .leftJoin(pointEntity).on(isEqualBoardSeq())
                .groupBy(boardEntity.boardSeq)
                .orderBy(boardEntity.createTime.desc())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
                .fetch();


        return toSlice(boardEntities);

    }

    @Override
    public Slice<BoardEntityDto> findOrderByPoint(Integer page) {
        List<BoardEntityDto> boardEntities = jpaQueryFactory
                .select(Projections.constructor(
                        BoardEntityDto.class,
                        boardEntity.boardSeq,
                        boardEntity.categorySeq,
                        boardEntity.author,
                        boardEntity.title,
                        pointEntity.point.sum()
                ))
                .from(boardEntity)
                .leftJoin(pointEntity).on(isEqualBoardSeq())
                .groupBy(boardEntity.boardSeq)
                .orderBy(pointEntity.point.sum().desc())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
                .fetch();

        return toSlice(boardEntities);
    }

    private BooleanExpression isContainsWord(String word) {
        return boardEntity.title.contains(word);
    }

    private BooleanExpression isEqualBoardSeq() {
        return boardEntity.boardSeq.eq(pointEntity.boardSeq);
    }

    private SliceImpl<BoardEntityDto> toSlice(List<BoardEntityDto> boardEntities) {
        boolean hasNext = boardEntities.size() > PAGE_SIZE;

        if (hasNext) {
            boardEntities.remove(boardEntities.size() - 1);
        }

        return new SliceImpl<>(boardEntities, Pageable.ofSize(PAGE_SIZE).withPage(PAGE_SIZE), hasNext);
    }
}
