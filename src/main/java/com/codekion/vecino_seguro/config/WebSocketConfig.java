package com.codekion.vecino_seguro.config;

import com.codekion.vecino_seguro.utils.ChatWebSocketHandler;
import com.codekion.vecino_seguro.utils.GuardarMensaje;
import com.codekion.vecino_seguro.utils.PanicButtonHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final GuardarMensaje guardarMensaje;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new PanicButtonHandler(), "/panic-button").setAllowedOrigins("*");
        registry.addHandler(new ChatWebSocketHandler(guardarMensaje), "/chat").setAllowedOrigins("*");
    }


}