package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.BotonPanico;
import com.codekion.vecino_seguro.model.dto.BotonPanicoResponseDto;
import com.codekion.vecino_seguro.model.dto.RequestBotonPanicoDto;
import com.codekion.vecino_seguro.service.Iservice.IBotonPanicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/boton-panico")
@RequiredArgsConstructor
public class BotonPanicoController {

    private final IBotonPanicoService botonPanicoService;

    @PostMapping("/activar")
    public ResponseEntity<?> activarBotonPanico(@RequestBody RequestBotonPanicoDto dto) {
        Map<String, Object> response = new HashMap<>();
        BotonPanico panico = botonPanicoService.activarBotonPanico(dto);
        BotonPanicoResponseDto botonPanicoResponseDto = new BotonPanicoResponseDto();
        botonPanicoResponseDto.setId(String.valueOf(panico.getId()));
        botonPanicoResponseDto.setNombres(panico.getUsuario().getNombre() + " " + panico.getUsuario().getApellidop() + " " + panico.getUsuario().getApellidom());
        botonPanicoResponseDto.setFechaActivacion(String.valueOf(panico.getFechaActivacion()));
        botonPanicoResponseDto.setLatitud(String.valueOf(panico.getLatitud()));
        botonPanicoResponseDto.setLongitud(String.valueOf(panico.getLongitud()));
        response.put("mensaje", "Boton de panico activado");
        response.put("botonPanico", botonPanicoResponseDto);
        return ResponseEntity.ok(response);
    }

}