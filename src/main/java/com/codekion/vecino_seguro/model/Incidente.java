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
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
@Table(name = "incidentes")
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incidentes_id_gen")
    @SequenceGenerator(name = "incidentes_id_gen", sequenceName = "incidentes_incidente_id_seq", allocationSize = 1)
    @Column(name = "incidente_id", nullable = false)
    private Integer id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_incidente")
    private TipoIncidente tipoIncidente;

    @Column(name = "descripcion", length = Integer.MAX_VALUE)
    private String descripcion;

    @NotNull
    @Column(name = "latitud", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitud;

    @NotNull
    @Column(name = "longitud", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitud;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_reporte")
    private Instant fechaReporte;


    @PrePersist
    public void prePersist() {
        fechaReporte = ZonedDateTime.now(ZoneId.of("America/Lima")).minus(5, ChronoUnit.HOURS).toInstant();
    }

}