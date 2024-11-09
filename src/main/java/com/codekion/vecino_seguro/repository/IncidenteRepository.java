package com.codekion.vecino_seguro.repository;

import com.codekion.vecino_seguro.model.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidenteRepository extends JpaRepository<Incidente, Integer> {

    @Query("SELECT i FROM Incidente i WHERE i.usuario.id = ?1")
    List<Incidente> findByIdUsuario(Integer idUsuario);
}