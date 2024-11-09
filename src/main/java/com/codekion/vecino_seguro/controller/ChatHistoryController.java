package com.codekion.vecino_seguro.controller;

import com.codekion.vecino_seguro.model.Mensaje;
import com.codekion.vecino_seguro.service.Iservice.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat-history")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final IChatService chatService;

    @GetMapping("/obtener")
    public List<Mensaje> getChatHistory() {
        return chatService.getChatHistory();
    }

}
