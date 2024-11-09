package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.model.dto.RegistrarDto;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import static com.codekion.vecino_seguro.utils.EntidadNoNulaException.verificarEntidadNoNula;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegistrarController {

    private final IUsuarioService usuarioService;

    @PostMapping(value = "register")
    public ResponseEntity<?> registrar(@RequestBody RegistrarDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            //Comprobamos parametros
            verificarEntidadNoNula(dto.getNombres(), "El nombre es requerido");
            verificarEntidadNoNula(dto.getApellidop(), "El apellido paterno es requerido");
            verificarEntidadNoNula(dto.getApellidom(), "El apellido materno es requerido");
            verificarEntidadNoNula(dto.getCorreo(), "El correo es requerido");
            verificarEntidadNoNula(dto.getPassword(), "La contraseña es requerida");

            //Verificamos que el correo no exista
            if (usuarioService.findByUsername(dto.getCorreo()).isPresent()) {
                return ResponseEntity.badRequest().body("El correo ya existe");
            }

            // Parseamos la fecha de nacimiento
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
            LocalDate fechaNacimiento;
            try {
                fechaNacimiento = LocalDate.parse(dto.getFechaNacimiento(), formatter);
            } catch (DateTimeParseException e) {
                return ResponseEntity.badRequest().body("Formato de fecha de nacimiento inválido");
            }

            //Creamos el usuario
            Usuario usuario = Usuario.builder()
                    .nombre(dto.getNombres())
                    .apellidop(dto.getApellidop())
                    .apellidom(dto.getApellidom())
                    .correo(dto.getCorreo())
                    .telefono(dto.getTelefono())
                    .password(dto.getPassword())
                    .fechaNacimiento(fechaNacimiento)
                    .build();

            //Guardamos el usuario
            usuarioService.save(usuario);

            response.put("message", "Usuario creado correctamente");
            response.put("data", usuario);
            response.put("status", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Error al crear el usuario: " + e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}