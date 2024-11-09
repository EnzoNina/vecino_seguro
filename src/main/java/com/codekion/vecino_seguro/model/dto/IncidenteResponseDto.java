package com.codekion.vecino_seguro.model.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class IncidenteResponseDto {

    private String id;
    private String tipo_incidente;
    private String descripcion;
    private String latitud;
    private String longitud;
    private Instant fecha_reporte;

}