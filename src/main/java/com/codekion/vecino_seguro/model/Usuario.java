package com.codekion.vecino_seguro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_id_gen")
    @SequenceGenerator(name = "usuarios_id_gen", sequenceName = "usuarios_usuario_id_seq", allocationSize = 1)
    @Column(name = "usuario_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 100)
    @NotNull
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Size(max = 255)
    @NotNull
    @Column(name = "contrasena_hash", nullable = false)
    private String password;

    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    private String telefono;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @Size(max = 100)
    @Column(name = "apellidop", length = 100)
    private String apellidop;

    @Size(max = 100)
    @Column(name = "apellidom", length = 100)
    private String apellidom;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> "ROLE_USER");
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = Instant.now();
    }
}