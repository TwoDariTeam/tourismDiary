package com.team.twodari.point.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "MP_POINT")
@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//MP 테이블임 이럴 때는? 찾아보자
public class PointEntity {

    // 포인트 일련번호
    @Id
    @Column(columnDefinition = "INT")
    private Long pointSeq;

    // 게시판 일련번호
    @Column(columnDefinition = "INT")
    private Long boardSeq;

    // 닉네임
    @Column(columnDefinition = "VARCHAR(40)")
    private String nickname;

    // 포인트
    @Column(columnDefinition = "INT")
    private Integer point;

}
