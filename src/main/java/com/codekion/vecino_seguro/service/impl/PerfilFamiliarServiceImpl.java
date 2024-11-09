package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.PerfilesFamiliare;
import com.codekion.vecino_seguro.service.Iservice.IPerfilFamiliarservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilFamiliarServiceImpl implements IPerfilFamiliarservice {

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