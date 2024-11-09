package com.codekion.vecino_seguro.model.dto;

import lombok.Data;

@Data
public class BotonPanicoResponseDto {

    private String id;
    private String nombres;
    private String latitud;
    private String longitud;
    private String fechaActivacion;

}