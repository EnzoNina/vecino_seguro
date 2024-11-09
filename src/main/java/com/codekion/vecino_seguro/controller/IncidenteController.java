package com.codekion.vecino_seguro.controller;


import com.codekion.vecino_seguro.model.Incidente;
import com.codekion.vecino_seguro.model.dto.IncidenteResponseDto;
import com.codekion.vecino_seguro.model.dto.RequestServiceDto;
import com.codekion.vecino_seguro.service.Iservice.IIncidenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/incidentes")
@RequiredArgsConstructor
public class IncidenteController {

    private final IIncidenteService incidenteService;

    @PostMapping("/reportarIncidente")
    public ResponseEntity<?> reportarIncidente(@RequestBody RequestServiceDto incidente) {
        Map<String, Object> response = new HashMap<>();
        Incidente reporte = incidenteService.reportarIncidente(incidente);
        IncidenteResponseDto incidenteResponseDto = new IncidenteResponseDto();
        setIncidenteResponseDto(reporte, incidenteResponseDto);
        response.put("mensaje", "Incidente reportado exitosamente");
        response.put("incidente", incidenteResponseDto);
        return ResponseEntity.ok(response);
    }

    private void setIncidenteResponseDto(Incidente reporte, IncidenteResponseDto incidenteResponseDto) {
        incidenteResponseDto.setId(String.valueOf(reporte.getId()));
        incidenteResponseDto.setTipo_incidente(reporte.getTipoIncidente().getDescripcion());
        incidenteResponseDto.setDescripcion(reporte.getDescripcion());
        incidenteResponseDto.setLatitud(String.valueOf(reporte.getLatitud()));
        incidenteResponseDto.setLongitud(String.valueOf(reporte.getLongitud()));
        incidenteResponseDto.setFecha_reporte(reporte.getFechaReporte());
    }

    @GetMapping("/listar/{usuarioId}")
    public ResponseEntity<?> listarIncidentesPorUsuario(@PathVariable Integer usuarioId) {
        Map<String, Object> response = new HashMap<>();
        List<Incidente> incidentes = incidenteService.listarIncidentesPorUsuario(usuarioId);
        List<IncidenteResponseDto> incidentesResponse = new ArrayList<>();
        for (Incidente incidente : incidentes) {
            IncidenteResponseDto incidenteResponseDto = new IncidenteResponseDto();
            setIncidenteResponseDto(incidente, incidenteResponseDto);
            incidentesResponse.add(incidenteResponseDto);
        }
        response.put("incidentes", incidentesResponse);
        response.put("mensaje", "Incidentes del usuario");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<?> listarTodosLosIncidentes() {
        Map<String, Object> response = new HashMap<>();
        List<Incidente> incidentes = incidenteService.listarIncidentes();
        List<IncidenteResponseDto> incidentesResponse = new ArrayList<>();
        for (Incidente incidente : incidentes) {
            IncidenteResponseDto incidenteResponseDto = new IncidenteResponseDto();
            setIncidenteResponseDto(incidente, incidenteResponseDto);
            incidentesResponse.add(incidenteResponseDto);
        }
        response.put("incidentes", incidentesResponse);
        response.put("mensaje", "Todos los incidentes");
        return ResponseEntity.ok(response);
    }

}