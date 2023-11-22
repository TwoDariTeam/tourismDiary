package com.team.twodari.reply.entity;

import com.team.twodari.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "TB_REPLY")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyEntity extends BaseEntity {
    // 댓글 일련번호
    @Id
    @Column(columnDefinition = "INT")
    private Long replySeq;

    // 게시판 일련번호
    @Column(columnDefinition = "INT")
    private Long boardSeq;

    // 작성자
    @Column(columnDefinition = "VARCHAR(40)")
    private String author;

    // 내용
    @Column(columnDefinition = "TEXT")
    private String contents;

    // 삭제플래그
    @Column(columnDefinition = "CHAR(1)")
    private String deleted;


}
