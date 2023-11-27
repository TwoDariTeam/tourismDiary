package com.team.twodari.global.infrastructure;

import com.team.twodari.global.infrastructure.dto.TourApiRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TourApiService {

    @Value("${TOUR_API.SERVICE_KEY}")
    public static String serviceKey;

    @Value("${TOUR_API.END_POINT}")
    public static String endPoint;

    public void getTourInfo(TourApiRequest request, Integer page) {

    }
}
