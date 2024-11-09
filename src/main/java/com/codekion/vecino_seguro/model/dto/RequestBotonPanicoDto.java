package com.codekion.vecino_seguro.model.dto;

import lombok.Data;

@Data
public class RequestBotonPanicoDto {

    private String usuarioId;
    private String latitud;
    private String longitud;

}