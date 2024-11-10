package com.codekion.vecino_seguro.utils;

import com.codekion.vecino_seguro.model.Mensaje;
import com.codekion.vecino_seguro.model.MessageType;
import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.service.Iservice.IChatService;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class GuardarMensaje {

    private final IChatService chatService;
    private final IUsuarioService usuarioService;

    public void almacenarMensaje(String user, String content) {

        // Find the user by name (assuming user names are unique)
        Usuario usuario = usuarioService.findByUsername(user).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Create a new Mensaje entity
        Mensaje mensaje = Mensaje.builder()
                .usuario(usuario)
                .contenido(content)
                .fechaEnvio(Instant.now())
                .tipo(MessageType.CHAT)
                .build();

        // Save the message to the database
        chatService.saveMessage(mensaje);
    }

}