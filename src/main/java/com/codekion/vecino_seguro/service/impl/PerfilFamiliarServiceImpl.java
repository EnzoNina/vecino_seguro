package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.PerfilesFamiliare;
import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.repository.PerfilFamiliarRepository;
import com.codekion.vecino_seguro.service.Iservice.IPerfilFamiliarservice;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerfilFamiliarServiceImpl implements IPerfilFamiliarservice {

    private final PerfilFamiliarRepository perfilFamiliarRepository;
    private final IUsuarioService usuarioService;

    // Método para verificar si el usuario es mayor de edad
    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }

    @Override
    public PerfilesFamiliare agregarPerfilFamiliar(PerfilesFamiliare perfilFamiliar) {
        return null;
    }

    @Override
    public List<PerfilesFamiliare> listarPerfilesFamiliares(Integer id_usuario) {
        return List.of();
    }

    @Override
    public void eliminarPerfilFamiliar(Integer id_perfil, Integer usuarioId) {

    }

    @Override
    public PerfilesFamiliare actualizarPerfilFamiliar(Integer id_perfil, Integer id_usuario, PerfilesFamiliare perfilFamiliar) {
        return null;
    }

    @Override
    public PerfilesFamiliare actualizarUbicacion(Integer id_perfil, BigDecimal latitud, BigDecimal longitud) {
        return null;
    }
}