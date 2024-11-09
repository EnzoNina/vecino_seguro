package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.BotonPanico;
import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.model.dto.RequestBotonPanicoDto;
import com.codekion.vecino_seguro.repository.BotonPanicoRepository;
import com.codekion.vecino_seguro.service.Iservice.IBotonPanicoService;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BotonPanicoServiceImpl implements IBotonPanicoService {

    private final BotonPanicoRepository botonPanicoRepository;
    private final IUsuarioService usuarioService;

    @Override
    public BotonPanico activarBotonPanico(RequestBotonPanicoDto botonPanico) {

        Usuario usuario = usuarioService.findById(Integer.valueOf(botonPanico.getUsuarioId()))
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        BotonPanico botonPanicoEntity = new BotonPanico();
        botonPanicoEntity.setUsuario(usuario);
        botonPanicoEntity.setLatitud(new BigDecimal(botonPanico.getLatitud()));
        botonPanicoEntity.setLongitud(new BigDecimal(botonPanico.getLongitud()));
        return botonPanicoRepository.save(botonPanicoEntity);
    }
}