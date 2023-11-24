package com.team.twodari.global.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.team.twodari.common.constant.Constant.PAGE_SIZE;

public class SliceConverter {
    public static <T> SliceImpl<T> toSlice(List<T> response) {
        boolean hasNext = response.size() > PAGE_SIZE;

        if (hasNext) {
            response.remove(response.size() - 1);
        }

        return new SliceImpl<>(response, Pageable.ofSize(PAGE_SIZE).withPage(PAGE_SIZE), hasNext);
    }
}
