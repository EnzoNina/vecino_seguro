package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.ContactosEmergencia;
import com.codekion.vecino_seguro.repository.ContactosEmergenciaRepository;
import com.codekion.vecino_seguro.service.Iservice.IContactosEmergenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactosEmergenciaServiceImpl implements IContactosEmergenciaService {

    private final ContactosEmergenciaRepository contactosEmergenciaRepository;

    @Override
    public List<ContactosEmergencia> findAll() {
        return contactosEmergenciaRepository.findAll();
    }
}
