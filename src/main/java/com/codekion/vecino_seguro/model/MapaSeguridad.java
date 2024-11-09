package com.codekion.vecino_seguro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "mapa_seguridad")
public class MapaSeguridad {
    @Id
    @ColumnDefault("nextval('mapa_seguridad_mapa_id_seq'::regclass)")
    @Column(name = "mapa_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;

    @NotNull
    @Column(name = "latitud", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitud;

    @NotNull
    @Column(name = "longitud", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitud;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}