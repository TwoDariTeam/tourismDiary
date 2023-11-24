package com.team.twodari.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
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
public class BoardMyPageRepositoryImpl implements BoardMyPageRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardOwnResponse> findOwnBoardOrderByCreateDate(String nickname, Integer page) {
        List<BoardOwnResponse> result = jpaQueryFactory
                .select(Projections.constructor(
                        BoardOwnResponse.class,
                        boardEntity.boardSeq,
                        boardEntity.categorySeq,
                        boardEntity.author,
                        boardEntity.title,
                        pointEntity.point.sum()
                ))
                .from(boardEntity)
                .leftJoin(pointEntity).on(boardEntity.boardSeq.eq(pointEntity.boardSeq))
                .where(boardEntity.author.eq(nickname))
                .groupBy(boardEntity.boardSeq)
                .orderBy(boardEntity.createTime.desc())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
                .fetch();

        return result;
    }

    @Override
    public List<BoardMyLikedResponse> findMyLikedBoardOrderByCreateDate(String nickname, Integer page) {
        List<BoardMyLikedResponse> result = jpaQueryFactory
                .select(Projections.constructor(
                        BoardMyLikedResponse.class,
                        boardEntity.boardSeq,
                        boardEntity.categorySeq,
                        boardEntity.author,
                        boardEntity.title,
                        pointEntity.point.sum()
                ))
                .from(pointEntity)
                .innerJoin(boardEntity).on(pointEntity.boardSeq.eq(boardEntity.boardSeq))
                .where(pointEntity.nickname.eq(nickname))
                .orderBy(boardEntity.createTime.desc())
                .offset(page * PAGE_SIZE)
                .limit(PAGE_SIZE + 1)
                .fetch();

        return result;
    }
}
