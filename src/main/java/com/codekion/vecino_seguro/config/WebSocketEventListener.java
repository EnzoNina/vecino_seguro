package com.codekion.vecino_seguro.config;

import com.codekion.vecino_seguro.model.Mensaje;
import com.codekion.vecino_seguro.model.MessageType;
import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;
    private final IUsuarioService usuarioService;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            Optional<Usuario> usuarioOpt = usuarioService.findByUsername(username);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                log.info("Usuario desconectado: {}", username);

                var chatMessage = Mensaje.builder()
                        .tipo(MessageType.LEAVE)
                        .usuario(usuario)
                        .build();

                messagingTemplate.convertAndSend("/topic/public", chatMessage);
            } else {
                log.warn("Usuario con username {} no encontrado en la base de datos", username);
            }
        } else {
            log.warn("Desconexión detectada sin username en SessionAttributes");
        }
    }

}