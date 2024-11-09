package com.codekion.vecino_seguro.service.Iservice;

import com.codekion.vecino_seguro.model.Mensaje;

import java.util.List;

public interface IChatService {

    Mensaje saveMessage(Mensaje mensaje);

    List<Mensaje> getChatHistory();

}