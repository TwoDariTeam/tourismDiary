package com.team.twodari.global.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TourApiRequest {

    private Integer pageNo;

    private Integer contentTypeId;

    private String parentRegion; //서울시
    private String childRegion; //강남구
}
