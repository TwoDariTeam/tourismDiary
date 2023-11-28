package com.team.twodari.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;


@Getter
@AllArgsConstructor
public class PageResponse<T> {
    private T data;
    private int pageNum;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;

    public static PageResponse of(Object data, Page page){
        return new PageResponse(
                data,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext());
    }
}
