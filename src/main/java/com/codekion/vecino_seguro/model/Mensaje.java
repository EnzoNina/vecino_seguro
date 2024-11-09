package com.codekion.vecino_seguro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "mensajes")
public class Mensaje {
    @Id
    @ColumnDefault("nextval('mensajes_mensaje_id_seq'::regclass)")
    @Column(name = "mensaje_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NotNull
    @Column(name = "contenido", nullable = false, length = Integer.MAX_VALUE)
    private String contenido;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_envio")
    private Instant fechaEnvio;

}