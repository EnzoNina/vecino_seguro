package com.codekion.vecino_seguro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "contactos_emergencia")
public class ContactosEmergencia {
    @Id
    @ColumnDefault("nextval('contactos_emergencia_contacto_id_seq'::regclass)")
    @Column(name = "contacto_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "tipo_servicio", nullable = false, length = 50)
    private String tipoServicio;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre_servicio", nullable = false, length = 50)
    private String nombreServicio;

    @Size(max = 15)
    @Column(name = "telefono_contacto", length = 15)
    private String telefonoContacto;

    @Column(name = "ubicacion_lat", precision = 10, scale = 8)
    private BigDecimal ubicacionLat;

    @Column(name = "ubicacion_long", precision = 11, scale = 8)
    private BigDecimal ubicacionLong;

}