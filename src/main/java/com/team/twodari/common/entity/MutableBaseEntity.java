package com.team.twodari.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class MutableBaseEntity extends BaseEntity {
    // 생성 시간
    @CreatedDate
    @Column(updatable = true, columnDefinition = "VARCHAR(40)")
    private LocalDateTime createTime;

    // 수정 시간
    @LastModifiedDate
    @Column(updatable = true, columnDefinition = "VARCHAR(40)")
    private String updateTime;


}
