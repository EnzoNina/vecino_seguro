package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.TipoIncidente;
import com.codekion.vecino_seguro.service.Iservice.ITipoIncidenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tiposIncidentes")
@RequiredArgsConstructor
public class TipoIncidenteController {

    private final ITipoIncidenteService tipoIncidenteService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarTiposIncidentes() {
        Map<String, Object> response = new HashMap<>();
        List<TipoIncidente> tiposIncidentes = tipoIncidenteService.findAll();
        response.put("tiposIncidentes", tiposIncidentes);
        response.put("mensaje", "Todos los tipos de incidentes");
        return ResponseEntity.ok(response);
    }

}