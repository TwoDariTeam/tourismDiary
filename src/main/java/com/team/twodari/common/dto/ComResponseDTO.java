package com.team.twodari.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComResponseDTO<T> {
    private String mesg;
    private T body;

    public ComResponseDTO(String mesg) {
        this.mesg = mesg;
    }

    public ComResponseDTO(String mesg, T body) {
        this.mesg = mesg;
        this.body = body;
    }
}
