package com.codekion.vecino_seguro.model.dto;

import lombok.Data;

@Data
public class RequestServiceDto {

    private String id_usuario;
    private String id_tipo_incidente;
    private String descripcion;
    private String latitud;
    private String longitud;

}