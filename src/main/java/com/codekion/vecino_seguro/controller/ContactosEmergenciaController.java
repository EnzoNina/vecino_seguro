package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.service.Iservice.IContactosEmergenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/servicios-emergencia")
@RequiredArgsConstructor
public class ContactosEmergenciaController {

    private final IContactosEmergenciaService contactosEmergenciaService;

    @GetMapping("/listar")
    public ResponseEntity<?> getContactosEmergencia() {
        Map<String, Object> response = new HashMap<>();
        response.put("contactos", contactosEmergenciaService.findAll());
        response.put("mensaje", "Contactos de emergencia");
        return ResponseEntity.ok(response);
    }

}