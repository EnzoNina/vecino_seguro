package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.PerfilesFamiliare;
import com.codekion.vecino_seguro.model.dto.RequestPerfilFamiliarDto;
import com.codekion.vecino_seguro.model.dto.ResponsePerfiFamiliarDto;
import com.codekion.vecino_seguro.service.Iservice.IPerfilFamiliarservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/perfiles-familiares")
@RequiredArgsConstructor
public class PerfilFamiliarController {


    private final IPerfilFamiliarservice perfilFamiliarservice;

    // Endpoint para agregar un perfil familiar
    @PostMapping("/agregarPerfil/{id_usuario}")
    public ResponseEntity<?> agregarPerfilFamiliar(@PathVariable Integer id_usuario, @RequestBody RequestPerfilFamiliarDto perfilFamiliar) {
        Map<String, Object> response = new HashMap<>();
        PerfilesFamiliare perfil = perfilFamiliarservice.agregarPerfilFamiliar(id_usuario, perfilFamiliar);
        ResponsePerfiFamiliarDto responsePerfiFamiliarDto = setDatosPerfilesFamiliaresDto(perfil);
        response.put("perfil", responsePerfiFamiliarDto);
        response.put("mensaje", "Perfil familiar agregado exitosamente");
        return ResponseEntity.ok(response);
    }

    // Endpoint para listar los perfiles familiares de un usuario
    @GetMapping("/listar/{id_usuario}")
    public ResponseEntity<?> listarPerfilesFamiliares(@PathVariable Integer id_usuario) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<PerfilesFamiliare> perfiles = perfilFamiliarservice.listarPerfilesFamiliares(id_usuario);
            List<ResponsePerfiFamiliarDto> responseDto = new ArrayList<>();
            for (PerfilesFamiliare perfil : perfiles) {
                ResponsePerfiFamiliarDto responsePerfiFamiliarDto = setDatosPerfilesFamiliaresDto(perfil);
                responseDto.add(responsePerfiFamiliarDto);
            }
            response.put("perfiles", responseDto);
            response.put("mensaje", "Perfiles familiares listados exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private ResponsePerfiFamiliarDto setDatosPerfilesFamiliaresDto(PerfilesFamiliare perfil) {
        ResponsePerfiFamiliarDto responsePerfiFamiliarDto = new ResponsePerfiFamiliarDto();
        responsePerfiFamiliarDto.setId(perfil.getId());
        responsePerfiFamiliarDto.setNombre_usuario(perfil.getId_usuario().getNombre());
        responsePerfiFamiliarDto.setApellido_usuario(perfil.getId_usuario().getApellidop());
        responsePerfiFamiliarDto.setNombre_familiar(perfil.getFamiliar_id().getNombre());
        responsePerfiFamiliarDto.setApellido_familiar(perfil.getFamiliar_id().getApellidop());
        responsePerfiFamiliarDto.setRelacion(perfil.getRelacion().getDescripcion());
        return responsePerfiFamiliarDto;
    }

    // Endpoint para eliminar un perfil familiar
    @DeleteMapping("/{id_perfil}/{usuarioId}")
    public ResponseEntity<?> eliminarPerfilFamiliar(@PathVariable Integer id_perfil, @PathVariable Integer usuarioId) {
        Map<String, Object> response = new HashMap<>();
        try {
            perfilFamiliarservice.eliminarPerfilFamiliar(id_perfil, usuarioId);
            response.put("mensaje", "Perfil familiar eliminado exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el perfil familiar: " + e.getMessage());
        }
    }

    // Endpoint para actualizar un perfil familiar
    @PutMapping("/{id_perfil}/{id_usuario}")
    public ResponseEntity<?> actualizarPerfilFamiliar(@PathVariable Integer id_perfil, @PathVariable Integer id_usuario, @RequestBody PerfilesFamiliare perfilFamiliar) {
        Map<String, Object> response = new HashMap<>();
        try {
            PerfilesFamiliare perfil = perfilFamiliarservice.actualizarPerfilFamiliar(id_perfil, id_usuario, perfilFamiliar);
            response.put("perfil", perfil);
            response.put("mensaje", "Perfil familiar actualizado exitosamente");
            return ResponseEntity.ok(response);
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
