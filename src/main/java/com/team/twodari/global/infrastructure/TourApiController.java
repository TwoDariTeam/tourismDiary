package com.team.twodari.global.infrastructure;

import com.team.twodari.global.infrastructure.dto.TourApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("tour/information")
public class TourApiController {
    private final TourApiService tourApiService;

    public String getInfo(
            @RequestBody TourApiRequest request,
            @RequestParam(defaultValue = "0") Integer page){

        tourApiService.getTourInfo(request, page);
        return "성공";
    }





}
