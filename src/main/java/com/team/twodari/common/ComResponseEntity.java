package com.team.twodari.common;

import com.team.twodari.common.dto.ComResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ComResponseEntity<T> extends ResponseEntity<ComResponseDTO<T>> {
    //본문에 데이터 담을 필요 없는 것들 insert update 그냥 결과만 알려도 될 때 사용합니다.
    public ComResponseEntity() {
        super(new ComResponseDTO<T>(), HttpStatus.OK);
    }

    //삭제 요청시 상태값만 반환할 때 필요함 인자 값으로 상태 값만 받을 때 사용합니다.
    public ComResponseEntity(HttpStatus status) {
        super(status);
    }

    //상태 코드와 함께 데이터 전달할 때 select 상태코드 변경 가능합니다.
    public ComResponseEntity(ComResponseDTO<T> body, HttpStatusCode status) {
        super(body, status);
    }

    //상대코드 없이 데이터 전달할 때만 보냅니다.
    public ComResponseEntity(ComResponseDTO<T> body) {
        super(body, HttpStatus.OK);
    }

    //토큰용


}
