package com.codekion.vecino_seguro.service.Iservice;

import com.codekion.vecino_seguro.model.ContactosEmergencia;

import java.util.List;

public interface IContactosEmergenciaService {

    List<ContactosEmergencia> findAll();

}