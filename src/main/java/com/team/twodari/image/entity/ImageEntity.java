package com.team.twodari.image.entity;

import com.team.twodari.common.entity.MutableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "TB_IMAGE")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageEntity extends MutableBaseEntity {
    // 이미지 일련번호
    @Id
    @Column(columnDefinition = "INT")
    private Integer imageSeq;

    // 보조 게시판 일련번호
    @Column(columnDefinition = "INT")
    private Integer subBoardSeq;

    // 경로
    @Column(columnDefinition = "VARCHAR(200)")
    private String path;

    // 삭제플래그
    @Column(columnDefinition = "CHAR(1)")
    private String deleted;


}
