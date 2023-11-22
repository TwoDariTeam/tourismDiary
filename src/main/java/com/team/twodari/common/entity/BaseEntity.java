package com.team.twodari.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//추가 진행
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    //생성 시간
    @Null
    @CreatedDate
    @Column(updatable = false, columnDefinition="DATETIME")
    private LocalDateTime createTime;

    // 수정 시간
    @Null
    @LastModifiedDate
    @Column( columnDefinition="DATETIME")
    private String updateTime;

    // 생성 이름
    @Null
    @CreatedBy
    @Column(updatable = false,columnDefinition="VARCHAR(40)")
    private String createName;

    // 수정 이름
    @NotBlank
    @LastModifiedBy
    @Column(columnDefinition="VARCHAR(40)")
    private String updateName;


}
