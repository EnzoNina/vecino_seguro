package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.model.dto.LoginDto;
import com.codekion.vecino_seguro.service.Iservice.IAuthenticacionService;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.codekion.vecino_seguro.utils.EntidadNoNulaException.verificarEntidadNoNula;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final IUsuarioService usuarioService;
    private final IAuthenticacionService authenticacionService;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            //Comprobamos parametros
            verificarEntidadNoNula(dto.getEmail(), "El correo es requerido");
            verificarEntidadNoNula(dto.getPassword(), "La contraseña es requerida");

            //Verificamos que el usuario exista y que la contraseña sea correcta
            Optional<Usuario> usuario = usuarioService.findByUsernameAndPassword(dto.getEmail(), dto.getPassword());
            //Generamos el jwt
            String token = authenticacionService.getToken(usuario.get());

            response.put("message", "Usuario logeado correctamente");
            response.put("token", token);
            response.put("data", usuario.get());
            response.put("status", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Error al logear el usuario: " + e.getMessage());
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
