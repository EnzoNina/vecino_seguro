package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.service.Iservice.ITipoRelacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/relaciones-familiares")
public class RelacionFamiliarController {

    private final ITipoRelacionService tipoRelacionService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarRelacionesFamiliares() {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("relaciones", tipoRelacionService.listarTiposRelacion());
            response.put("mensaje", "Relaciones familiares listadas exitosamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("mensaje", "Error al listar las relaciones familiares");
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}