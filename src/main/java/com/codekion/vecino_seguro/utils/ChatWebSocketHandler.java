package com.codekion.vecino_seguro.utils;

import com.codekion.vecino_seguro.model.Mensaje;
import com.codekion.vecino_seguro.model.MessageType;
import com.codekion.vecino_seguro.model.Usuario;
import com.codekion.vecino_seguro.service.Iservice.IChatService;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Parse the message payload to extract user information and message content
        String payload = message.getPayload();
        JsonNode jsonNode = objectMapper.readTree(payload);
        String user = jsonNode.get("user").asText();
        String content = jsonNode.get("message").asText();

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

        // Broadcast the message to all connected clients
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