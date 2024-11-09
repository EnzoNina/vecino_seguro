package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.model.dto.ResponseUsuarioBuscarDto;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @GetMapping("/listarUsuarios")
    public ResponseEntity<?> listarUsuarios() {
        Map<String, Object> response = new HashMap<>();
        List<ResponseUsuarioBuscarDto> responseDto = new ArrayList<>();
        List<Usuario> usuarios = usuarioService.findAll();
        try {
            for (Usuario usuario : usuarios) {
                ResponseUsuarioBuscarDto responseUsuarioBuscarDto = new ResponseUsuarioBuscarDto();
                responseUsuarioBuscarDto.setId_usuario(usuario.getId().toString());
                responseUsuarioBuscarDto.setNombres_completos(usuario.getNombre() + " " + usuario.getApellidop() + " " + usuario.getApellidom());
                responseDto.add(responseUsuarioBuscarDto);
            }
            response.put("usuarios", responseDto);
            response.put("mensaje", "Usuarios listados exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("mensaje", "Error al listar los usuarios");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}