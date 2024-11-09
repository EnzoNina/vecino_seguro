package com.codekion.vecino_seguro.service.Iservice;

import com.codekion.vecino_seguro.model.PerfilesFamiliare;
import com.codekion.vecino_seguro.model.dto.RequestPerfilFamiliarDto;

import java.math.BigDecimal;
import java.util.List;

public interface IPerfilFamiliarservice {

    PerfilesFamiliare agregarPerfilFamiliar(Integer id_usuario, RequestPerfilFamiliarDto perfilFamiliar);

    List<PerfilesFamiliare> listarPerfilesFamiliares(Integer id_usuario);

    void eliminarPerfilFamiliar(Integer id_perfil,Integer usuarioId);

    PerfilesFamiliare actualizarPerfilFamiliar(Integer id_perfil,Integer id_usuario, PerfilesFamiliare perfilFamiliar);

    PerfilesFamiliare actualizarUbicacion(Integer id_perfil, BigDecimal latitud, BigDecimal longitud);

}