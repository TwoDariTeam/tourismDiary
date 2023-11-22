package com.team.twodari.category.entity;

import com.team.twodari.common.entity.MutableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryEntity extends MutableBaseEntity {
    // 카테고리 일련번호
    @Id
    @Column(columnDefinition = "INT")
    private Long categorySeq;

    // 카테고리
    @Column(columnDefinition = "VARCHAR(50)")
    private String category;

    // 접근 권한
    @Column(columnDefinition = "INT")
    private Integer accessRole;


}
