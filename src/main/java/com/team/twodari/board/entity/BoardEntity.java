package com.team.twodari.board.entity;

import com.team.twodari.common.entity.BaseEntity;
import com.team.twodari.image.entity.BoardImageEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "TB_BOARD")
@Entity
@Builder
@ToString(callSuper = true)
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseEntity {
    //시퀀스 네임 찾아서 여기서 주기.
    // 게시판 일련번호
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long boardSeq;

    // 카테고리 일련번호
    @Column(columnDefinition = "INT")
    private Long categorySeq;
    // 작성자
    @Column(columnDefinition = "VARCHAR(40)")
    private String author;

    // 제목
    @Column(columnDefinition = "VARCHAR(400)")
    private String title;

    // 소개글
    @Column(columnDefinition = "TEXT")
    private String introduce;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(30)")
    private BoardLocation location;

    // 접근 권한
    @Column(columnDefinition = "INT")
    private Integer accessRole;

    // 삭제플래그
    @Column(columnDefinition = "CHAR(1)")
    private String deleted;

    @OneToMany(mappedBy = "board")
    private List<BoardImageEntity> images = new ArrayList<>();

    public void updateEntity(String title, String introduce, Integer accessRole) {
        this.title = title;
        this.introduce = introduce;
        this.accessRole = accessRole;
    }

    public void deleteEntity() {
        this.deleted = "Y";

        for (BoardImageEntity image : images) {
            image.deleteEntity();
        }
    }
}
