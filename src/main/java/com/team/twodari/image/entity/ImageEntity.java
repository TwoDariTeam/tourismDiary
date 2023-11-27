package com.team.twodari.image.entity;

import com.team.twodari.common.entity.BaseEntity;
import com.team.twodari.subBoard.entity.SubBoardEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_IMAGE")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageEntity extends BaseEntity {
    // 이미지 일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long imageSeq;

    // 경로
    @Column(columnDefinition = "VARCHAR(200)")
    private String path;

    // 삭제플래그
    @Column(columnDefinition = "CHAR(1)")
    private String deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_board_seq", nullable = false)
    private SubBoardEntity subBoard;

    public void deleteEntity() {
        this.deleted = "Y";
    }
}
