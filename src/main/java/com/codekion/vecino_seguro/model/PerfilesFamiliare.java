package com.codekion.vecino_seguro.model;

import jakarta.persistence.*;
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
@Table(name = "perfiles_familiares")
public class PerfilesFamiliare {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfiles_familiares_id_gen")
    @SequenceGenerator(name = "perfiles_familiares_id_gen", sequenceName = "perfiles_familiares_perfil_id_seq", allocationSize = 1)
    @Column(name = "perfil_id", nullable = false)
    private Integer id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_jefe_id")
    private Usuario usuarioJefe;

    @ManyToOne
    @JoinColumn(name = "usuario_familia_id")
    private Usuario usuarioFamilia;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "relacion_id")
    private TiposRelacion relacion;

    @Column(name = "latitud", precision = 10, scale = 8)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 11, scale = 8)
    private BigDecimal longitud;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_ultima_ubicacion")
    private Instant fechaUltimaUbicacion;

}