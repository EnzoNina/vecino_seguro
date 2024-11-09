package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.PerfilesFamiliare;
import com.codekion.vecino_seguro.model.dto.RequestPerfilFamiliarDto;
import com.codekion.vecino_seguro.service.Iservice.IPerfilFamiliarservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/perfiles-familiares")
@RequiredArgsConstructor
public class PerfilFamiliarController {


    private final IPerfilFamiliarservice perfilFamiliarservice;

    // Endpoint para agregar un perfil familiar
    @PostMapping("/agregarPerfil/{id_usuario}")
    public ResponseEntity<PerfilesFamiliare> agregarPerfilFamiliar(@PathVariable Integer id_usuario, @RequestBody RequestPerfilFamiliarDto perfilFamiliar) {
        return null;
    }

    // Endpoint para listar los perfiles familiares de un usuario
    @GetMapping("/{id_usuario}")
    public ResponseEntity<List<PerfilesFamiliare>> listarPerfilesFamiliares(@PathVariable Integer id_usuario) {
        try {
            List<PerfilesFamiliare> perfiles = perfilFamiliarservice.listarPerfilesFamiliares(id_usuario);
            return ResponseEntity.ok(perfiles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint para eliminar un perfil familiar
    @DeleteMapping("/{id_perfil}/{usuarioId}")
    public ResponseEntity<String> eliminarPerfilFamiliar(@PathVariable Integer id_perfil, @PathVariable Integer usuarioId) {
        try {
            perfilFamiliarservice.eliminarPerfilFamiliar(id_perfil, usuarioId);
            return ResponseEntity.ok("Perfil familiar eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el perfil familiar: " + e.getMessage());
        }
    }

    // Endpoint para actualizar un perfil familiar
    @PutMapping("/{id_perfil}/{id_usuario}")
    public ResponseEntity<PerfilesFamiliare> actualizarPerfilFamiliar(@PathVariable Integer id_perfil, @PathVariable Integer id_usuario, @RequestBody PerfilesFamiliare perfilFamiliar) {
        try {
            PerfilesFamiliare perfil = perfilFamiliarservice.actualizarPerfilFamiliar(id_perfil, id_usuario, perfilFamiliar);
            return ResponseEntity.ok(perfil);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint para actualizar la ubicación de un perfil familiar
    @PutMapping("/{id_perfil}/ubicacion")
    public ResponseEntity<PerfilesFamiliare> actualizarUbicacion(@PathVariable Integer id_perfil, @RequestParam BigDecimal latitud, @RequestParam BigDecimal longitud) {
        try {
            PerfilesFamiliare perfil = perfilFamiliarservice.actualizarUbicacion(id_perfil, latitud, longitud);
            return ResponseEntity.ok(perfil);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
