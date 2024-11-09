package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.Mensaje;
import com.codekion.vecino_seguro.model.MessageType;
import com.codekion.vecino_seguro.service.Iservice.IChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements IChatService {

    private final ChatRepository chatRepository;

    @Override
    public Mensaje saveMessage(Mensaje mensaje) {
        // Validación según el tipo de mensaje
        if (mensaje.getTipo() == MessageType.IMAGE && mensaje.getUrlImagen() == null) {
            throw new IllegalArgumentException("La URL de la imagen es obligatoria para mensajes de tipo IMAGE");
        }
        if (mensaje.getTipo() == MessageType.CHAT && mensaje.getContenido() == null) {
            throw new IllegalArgumentException("El contenido de texto es obligatorio para mensajes de tipo CHAT");
        }

        // Guardar el mensaje en la base de datos
        return chatRepository.save(mensaje);
    }

    @Override
    public List<Mensaje> getChatHistory() {
        return chatRepository.findAll();
    }
}
