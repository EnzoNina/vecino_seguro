package com.codekion.vecino_seguro.utils;

import com.codekion.vecino_seguro.model.Mensaje;
import com.codekion.vecino_seguro.model.MessageType;
import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.service.Iservice.IChatService;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
    private final IChatService chatService;
    private final IUsuarioService usuarioService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        broadcast(message.getPayload());
        String payload = message.getPayload();

        String[] parts = payload.split(":", 2);
        Integer userId = Integer.parseInt(parts[0]);
        String content = parts[1];

        Usuario usuario = usuarioService.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Mensaje mensaje = Mensaje.builder()
                .usuario(usuario)
                .contenido(content)
                .fechaEnvio(Instant.now())
                .tipo(MessageType.CHAT)
                .build();

        chatService.saveMessage(mensaje);

        broadcast(message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    public static void broadcast(String message) {
        System.out.println("Broadcasting TEST: " + message);
        synchronized (sessions) {
            for (WebSocketSession session : sessions) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}