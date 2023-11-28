package com.team.twodari.board.support;

import com.team.twodari.board.dto.BoardMyLikedResponse;
import com.team.twodari.board.dto.BoardOwnResponse;
import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.image.entity.BoardImageEntity;

import java.time.LocalDateTime;
import java.util.List;

public class BoardFixture {
    private static final Long categorySeq = 1L;
    private static final String author = "글쓴이";
    private static final String title = "테스트 게시글 제목";
    private static final Integer accessRole = 1;
    private static final String deleted = null;
    private static final LocalDateTime writeDate = LocalDateTime.now();
    private static final List<String> tags = List.of("서울여행", "서울", "테스트");
    private static final String introduce = "부산 여행에 대한 포스팅을 시작합니다.";
    private static final List<String> imageUrls = List.of("https://besp1-2team-s3-bucket.s3.amazonaws.com/bda0087c-7969-4d9d-976e-6b0c0e564768-image.jpeg");

    public static String getImageUrl(){
        return imageUrls.get(0);
    }

    //Board 데이터 생성
    public static BoardEntity board(Long seq) {
        return BoardEntity.builder()
                .boardSeq(seq)
                .categorySeq(categorySeq)
                .author(author)
                .title(title)
                .accessRole(accessRole)
                .deleted(deleted)
                .introduce(introduce)
                .build();
    }

    public static BoardImageEntity boardImageEntity(Long seq, BoardEntity board) {
        return BoardImageEntity.builder()
                .boardImageSeq(seq)
                .path(imageUrls.get(0))
                .deleted(null)
                .board(board)
                .build();
    }

    public static BoardOwnResponse findOwnBoardOrderByCreateDate(Long seq) {
        BoardOwnResponse boardOwnResponse = new BoardOwnResponse(
                seq,
                categorySeq,
                author,
                title,
                accessRole,
                tags,
                writeDate,
                introduce,
                imageUrls
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
                tags,
                writeDate,
                introduce,
                imageUrls
        );

        return boardMyLikedResponse;
    }




}
