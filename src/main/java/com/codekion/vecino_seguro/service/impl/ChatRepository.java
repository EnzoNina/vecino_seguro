package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Mensaje, Integer> {
}