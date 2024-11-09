package com.codekion.vecino_seguro.repository;

import com.codekion.vecino_seguro.model.TipoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoIncidenteRepository extends JpaRepository<TipoIncidente, Integer> {
}