package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.PerfilesFamiliare;
import com.codekion.vecino_seguro.model.TiposRelacion;
import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.model.dto.RequestPerfilFamiliarDto;
import com.codekion.vecino_seguro.model.dto.RequestUpdatePerfilFamiliarDto;
import com.codekion.vecino_seguro.repository.PerfilFamiliarRepository;
import com.codekion.vecino_seguro.service.Iservice.IPerfilFamiliarservice;
import com.codekion.vecino_seguro.service.Iservice.ITipoRelacionService;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerfilFamiliarServiceImpl implements IPerfilFamiliarservice {

    private final PerfilFamiliarRepository perfilFamiliarRepository;
    private final IUsuarioService usuarioService;
    private final ITipoRelacionService tipoRelacionService;

    // Método para verificar si el usuario es mayor de edad
    private boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }


    private TiposRelacion obtenerRelacionInversa(TiposRelacion relacion) {
        Integer idInverso;
        switch (relacion.getId()) {
            case 1: // Padre
                idInverso = 2; // Hijo
                break;
            case 2: // Hijo
                idInverso = 1; // Padre
                break;
            case 3: // Pareja
                idInverso = 3; // Pareja
                break;
            default:
                throw new IllegalArgumentException("Relación no válida para crear inversa");
        }
        return tipoRelacionService.buscarTipoRelacion(idInverso);
    }


    @Transactional
    @Override
    public PerfilesFamiliare agregarPerfilFamiliar(Integer id_usuario, RequestPerfilFamiliarDto perfilFamiliarDto) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id_usuario);
        Optional<Usuario> usuarioFamiliarOpt = usuarioService.findById(Integer.valueOf(perfilFamiliarDto.getId_usuario_agregar()));
        if (usuarioOpt.isPresent() && usuarioFamiliarOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            Usuario usuarioFamiliar = usuarioFamiliarOpt.get();
            // Verificar si el usuario es mayor de edad
            if (!esMayorDeEdad(usuario.getFechaNacimiento())) {
                throw new SecurityException("Solo los usuarios mayores de edad pueden agregar perfiles familiares");
            }

            // Buscamos el tipo relacion
            TiposRelacion tipoRelacion = tipoRelacionService.buscarTipoRelacion(Integer.valueOf(perfilFamiliarDto.getId_tipo_relacion()));
            TiposRelacion relacionInversa = obtenerRelacionInversa(tipoRelacion);


            //Creamos el perfil familiar
            PerfilesFamiliare perfilFamiliarPrincipal = PerfilesFamiliare.builder()
                    .id_usuario(usuario)
                    .familiar_id(usuarioFamiliar)
                    .relacion(tipoRelacion)
                    .build();

            PerfilesFamiliare perfilFamiliarInverso = PerfilesFamiliare.builder()
                    .id_usuario(usuarioFamiliar)
                    .familiar_id(usuario)
                    .relacion(relacionInversa)
                    .build();

            perfilFamiliarRepository.save(perfilFamiliarInverso);

            return perfilFamiliarRepository.save(perfilFamiliarPrincipal);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    @Override
    public List<PerfilesFamiliare> listarPerfilesFamiliares(Integer id_usuario) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id_usuario);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return perfilFamiliarRepository.findByUsuario(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    @Override
    public void eliminarPerfilFamiliar(Integer id_perfil, Integer usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Verificar si el usuario es mayor de edad
            if (!esMayorDeEdad(usuario.getFechaNacimiento())) {
                throw new SecurityException("Solo los usuarios mayores de edad pueden eliminar perfiles familiares");
            }

            // Eliminar el perfil familiar
            perfilFamiliarRepository.deleteById(id_perfil);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    @Override
    public PerfilesFamiliare actualizarPerfilFamiliar(Integer id_perfil, Integer id_usuario, RequestUpdatePerfilFamiliarDto perfilFamiliar) {
        Optional<Usuario> usuarioOpt = usuarioService.findById(id_usuario);
        TiposRelacion tipoRelacion = tipoRelacionService.buscarTipoRelacion(Integer.valueOf(perfilFamiliar.getId_tipo_relacion()));
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Verificar si el usuario es mayor de edad
            if (!esMayorDeEdad(usuario.getFechaNacimiento())) {
                throw new SecurityException("Solo los usuarios mayores de edad pueden actualizar perfiles familiares");
            }

            // Buscar perfil familiar existente
            Optional<PerfilesFamiliare> perfilExistente = perfilFamiliarRepository.findById(id_perfil);
            if (perfilExistente.isPresent()) {
                PerfilesFamiliare perfil = perfilExistente.get();
                perfil.setRelacion(tipoRelacion);
                perfil.setFechaUltimaUbicacion(ZonedDateTime.now(ZoneId.of("America/Lima")).toInstant());
                return perfilFamiliarRepository.save(perfil);
            } else {
                throw new IllegalArgumentException("Perfil familiar no encontrado");
            }
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }

    @Override
    public PerfilesFamiliare actualizarUbicacion(Integer id_perfil, BigDecimal latitud, BigDecimal longitud) {
        Optional<PerfilesFamiliare> perfilExistente = perfilFamiliarRepository.findById(id_perfil);
        if (perfilExistente.isPresent()) {
            PerfilesFamiliare perfil = perfilExistente.get();
            perfil.setLatitud(latitud);
            perfil.setLongitud(longitud);
            perfil.setFechaUltimaUbicacion(ZonedDateTime.now(ZoneId.of("America/Lima")).toInstant());

            return perfilFamiliarRepository.save(perfil);
        } else {
            throw new IllegalArgumentException("Perfil familiar no encontrado");
        }
    }

}

