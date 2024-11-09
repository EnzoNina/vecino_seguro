package com.codekion.vecino_seguro.model.dto;

import lombok.Data;

@Data
public class RegistrarDto {
    private String nombres;
    private String apellidop;
    private String apellidom;
    private String correo;
    private String password;
    private String telefono;
    private String fechaNacimiento;
}
