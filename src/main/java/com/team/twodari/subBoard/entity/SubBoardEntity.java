package com.team.twodari.subBoard.entity;

import com.team.twodari.board.entity.BoardEntity;
import com.team.twodari.common.entity.BaseEntity;
import com.team.twodari.image.entity.SubBoardImageEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "TB_SUB_BOARD")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubBoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Long subBoardSeq;

    @Column(columnDefinition = "INT")
    private Long indexNumber;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column(columnDefinition = "CHAR(1)")
    private String deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_seq", nullable = false)
    private BoardEntity board;

    @OneToMany(mappedBy = "subBoard")
    private List<SubBoardImageEntity> images = new ArrayList<>();

    public void updateEntity(String contents) {
        this.contents = contents;
    }

    public void deleteEntity() {
        this.deleted = "Y";

        for (SubBoardImageEntity image : images) {
            image.deleteEntity();
        }
    }
}

