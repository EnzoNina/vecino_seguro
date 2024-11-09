package com.codekion.vecino_seguro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipos_relacion")
public class TiposRelacion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipos_relacion_id_gen")
    @SequenceGenerator(name = "tipos_relacion_id_gen", sequenceName = "tipos_relacion_relacion_id_seq", allocationSize = 1)
    @Column(name = "relacion_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

}