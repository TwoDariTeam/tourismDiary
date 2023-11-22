package com.team.twodari.tag.entity;

import com.team.twodari.common.entity.MutableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "TB_TAG")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity extends MutableBaseEntity {
    // 태그 일련번호
    @Id
    @Column(columnDefinition = "INT")
    private Integer tagSeq;

    // 게시판 일련번호
    @Column(columnDefinition = "INT")
    private Integer boardSeq;

    // 태그
    @Column(columnDefinition = "VARCHAR(50)")
    private String tag;
}
