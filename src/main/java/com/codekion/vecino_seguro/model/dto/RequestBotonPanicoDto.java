package com.codekion.vecino_seguro.model.dto;

import lombok.Data;

@Data
public class RequestBotonPanicoDto {

    private String id_usuario;
    private String latitud;
    private String longitud;

}