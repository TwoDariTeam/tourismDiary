package com.team.twodari.global.util;

import static com.team.twodari.common.constant.Constant.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;

public class SliceConverter {
	public static <T> SliceImpl<T> toSlice(List<T> response, int currentPage) {
		boolean hasNext = response.size() > PAGE_SIZE;

		if (hasNext) {
			response.remove(response.size() - 1);
		}

		return new SliceImpl<>(response, Pageable.ofSize(PAGE_SIZE).withPage(currentPage), hasNext);
	}
}
