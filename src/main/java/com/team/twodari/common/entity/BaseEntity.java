package com.team.twodari.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//추가 진행
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    //생성 시간
    @CreatedDate
    @Column(updatable = false, columnDefinition = "DATETIME")
    private LocalDateTime createTime;

    // 수정 시간
    @LastModifiedDate
    @Column(columnDefinition = "DATETIME")
    private String updateTime;

    // 생성 이름
    @CreatedBy
    @Column(updatable = false, columnDefinition = "VARCHAR(40)")
    private String createName;

    // 수정 이름
    @LastModifiedBy
    @Column(columnDefinition = "VARCHAR(40)")
    private String updateName;


}
