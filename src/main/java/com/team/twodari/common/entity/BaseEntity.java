package com.team.twodari.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//추가 진행
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // 생성 이름
    @CreatedBy
    @Column(updatable = false, columnDefinition = "DATETIME")
    private String createName;

    // 수정 이름
    @LastModifiedBy
    @Column(updatable = false, columnDefinition = "DATETIME")
    private String updateName;
}
