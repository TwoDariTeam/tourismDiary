package com.team.twodari.board.support;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.entity.BoardEntity;

import java.util.List;

public class BoardFixture {
    private static final Long categorySeq = 1L;
    private static final String author = "글쓴이";
    private static final String title = "테스트 게시글 제목";
    private static final Integer accessRole = 1;
    private static final String deleted = null;
    private static final List<String> tags = List.of("서울여행", "서울", "테스트");

    //Board 데이터 생성
    public static BoardEntity board(Long seq){
        return BoardEntity.builder()
                .boardSeq(seq)
                .categorySeq(categorySeq)
                .author(author)
                .title(title)
                .accessRole(accessRole)
                .deleted(deleted)
                .build();
    }

    public static BoardOwnResponse findOwnBoardOrderByCreateDate(Long seq) {
        BoardOwnResponse boardOwnResponse = new BoardOwnResponse(
                seq,
                categorySeq,
                author,
                title,
                accessRole,
                tags
        );

        return boardOwnResponse;
    }

    public static BoardMyLikedResponse findMyLikedBoardOrderByCreateDate(Long seq) {
        BoardMyLikedResponse boardMyLikedResponse = new BoardMyLikedResponse(
                seq,
                categorySeq,
                author,
                title,
                accessRole,
                tags
        );

        return boardMyLikedResponse;
    }




}
