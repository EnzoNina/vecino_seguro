package com.codekion.vecino_seguro.repository;

import com.codekion.vecino_seguro.model.PerfilesFamiliare;
import com.codekion.vecino_seguro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerfilFamiliarRepository extends JpaRepository<PerfilesFamiliare, Integer> {

    @Query("SELECT p FROM PerfilesFamiliare p WHERE p.usuarioJefe = ?1 or p.usuarioFamilia = ?1")
    List<PerfilesFamiliare> findByUsuario(Usuario usuario);
}