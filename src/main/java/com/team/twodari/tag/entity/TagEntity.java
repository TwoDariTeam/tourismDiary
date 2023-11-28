package com.team.twodari.tag.entity;

import com.team.twodari.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_TAG")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity extends BaseEntity {
    // 태그 일련번호
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT")
    private Integer tagSeq;

    // 게시판 일련번호
    @Column(columnDefinition = "INT")
    private Integer boardSeq;

    // 태그
    @Column(columnDefinition = "VARCHAR(50)")
    private String tag;
}
