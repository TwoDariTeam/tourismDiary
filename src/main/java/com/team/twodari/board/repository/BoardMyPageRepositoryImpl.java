package com.team.twodari.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.image.entity.BoardImageEntity;
import com.team.twodari.image.entity.QBoardImageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

import static com.team.twodari.board.entity.QBoardEntity.boardEntity;
import static com.team.twodari.common.constant.Constant.PAGE_SIZE;
import static com.team.twodari.image.entity.QBoardImageEntity.*;
import static com.team.twodari.point.entity.QPointEntity.pointEntity;

@RequiredArgsConstructor
@Repository
public class BoardMyPageRepositoryImpl implements BoardMyPageRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<BoardOwnResponse> findOwnBoardOrderByCreateDate(String nickname, Integer page) {
        List<BoardOwnResponse> result = jpaQueryFactory
                .select(Projections.constructor(
                        BoardOwnResponse.class,
                        boardEntity.boardSeq,
                        boardEntity.categorySeq,
                        boardEntity.author,
                        boardEntity.title,
                        pointEntity.point.sum(),
                        boardEntity.createTime,
                        boardEntity.introduce
                ))
                .from(boardEntity)
                .leftJoin(pointEntity).on(boardEntity.boardSeq.eq(pointEntity.boardSeq))
                .where(boardEntity.author.eq(nickname))
                .groupBy(boardEntity.boardSeq)
                .orderBy(boardEntity.createTime.desc())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
                .fetch();

        Long count = jpaQueryFactory		// (4)
                .select(boardEntity.count())
                .from(boardEntity)
                .where(boardEntity.author.eq(nickname))
                .fetchOne();

        return new PageImpl<>(result, PageRequest.of(page, PAGE_SIZE), count);
    }

    @Override
    public Page<BoardMyLikedResponse> findMyLikedBoardOrderByCreateDate(String nickname, Integer page) {
        List<BoardMyLikedResponse> result = jpaQueryFactory
                .select(Projections.constructor(
                        BoardMyLikedResponse.class,
                        boardEntity.boardSeq,
                        boardEntity.categorySeq,
                        boardEntity.author,
                        boardEntity.title,
                        pointEntity.point.sum(),
                        boardEntity.createTime,
                        boardEntity.introduce
                ))
                .from(pointEntity)
                .innerJoin(boardEntity).on(pointEntity.boardSeq.eq(boardEntity.boardSeq))
                .where(pointEntity.nickname.eq(nickname))
                .orderBy(boardEntity.createTime.desc())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
                .fetch();

        Long count = jpaQueryFactory		// (4)
                .select(pointEntity.count())
                .from(pointEntity)
                .innerJoin(boardEntity).on(pointEntity.boardSeq.eq(boardEntity.boardSeq))
                .where(pointEntity.nickname.eq(nickname))
                .fetchOne();

        return new PageImpl<>(result, PageRequest.of(page, PAGE_SIZE), count);
    }
}
