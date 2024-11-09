package com.codekion.vecino_seguro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "boton_panico")
public class BotonPanico {
    @Id
    @ColumnDefault("nextval('boton_panico_panico_id_seq'::regclass)")
    @Column(name = "panico_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_activacion")
    private Instant fechaActivacion;

    @NotNull
    @Column(name = "latitud", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitud;

    @NotNull
    @Column(name = "longitud", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitud;

    @PrePersist
    public void prePersist() {
        fechaActivacion = Instant.now();
    }

}