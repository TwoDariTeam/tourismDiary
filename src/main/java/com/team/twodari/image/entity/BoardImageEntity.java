package com.team.twodari.image.entity;

import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_BOARD_IMAGE")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long boardImageSeq;

    @Column(columnDefinition = "VARCHAR(200)")
    private String path;

    @Column(columnDefinition = "CHAR(1)")
    private String deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_seq", nullable = false)
    private BoardEntity board;

    public void deleteEntity() {
        this.deleted = "Y";
    }
}
