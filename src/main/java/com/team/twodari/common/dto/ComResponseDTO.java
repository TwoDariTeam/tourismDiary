package com.team.twodari.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;

@Getter
@Setter
@NoArgsConstructor
public class ComResponseDTO<T> {
    private String mesg;
    private HttpHeaders headers;
    private T body;

    public ComResponseDTO(String mesg) {
        this.mesg = mesg;
    }

    public ComResponseDTO(String mesg, T body) {
        this.mesg = mesg;
        this.body = body;
    }

    public  ComResponseDTO(HttpHeaders headers){
            this.headers=headers;
    }


}
