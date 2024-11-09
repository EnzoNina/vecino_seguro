package com.codekion.vecino_seguro.model.dto;

import lombok.Data;

@Data
public class ResponsePerfiFamiliarDto {

    private Integer id;
    private String nombre_usuario;
    private String apellido_usuario;
    private String nombre_familiar;
    private String apellido_familiar;
    private String relacion;

}