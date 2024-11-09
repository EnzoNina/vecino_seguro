package com.codekion.vecino_seguro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "info_seguridad")
public class InfoSeguridad {
    @Id
    @ColumnDefault("nextval('info_seguridad_info_id_seq'::regclass)")
    @Column(name = "info_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @Size(max = 100)
    @NotNull
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;

    @Size(max = 15)
    @Column(name = "numero_contacto", length = 15)
    private String numeroContacto;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}