package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.Mensaje;
import com.codekion.vecino_seguro.service.Iservice.IChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log
public class ChatController {

    private final IChatService chatService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Mensaje sendMessage(Mensaje message) {
        // Aquí podrías almacenar el mensaje en la base de datos antes de retornarlo
        log.info("Mensaje recibido: " + message);
        return chatService.saveMessage(message);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Mensaje addUser(
            @Payload Mensaje chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getUsuario().getUsername());
        return chatMessage;
    }

}