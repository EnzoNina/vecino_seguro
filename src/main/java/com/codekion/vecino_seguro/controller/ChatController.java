package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.Mensaje;
import com.codekion.vecino_seguro.service.Iservice.IChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log
public class ChatController {

    private final IChatService chatService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public Mensaje sendMessage(Mensaje message) {
        // Aquí podrías almacenar el mensaje en la base de datos antes de retornarlo
        log.info("Mensaje recibido: " + message);
        return chatService.saveMessage(message);

    }

}