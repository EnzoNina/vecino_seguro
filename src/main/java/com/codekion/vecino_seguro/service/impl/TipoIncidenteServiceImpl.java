package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.TipoIncidente;
import com.codekion.vecino_seguro.repository.TipoIncidenteRepository;
import com.codekion.vecino_seguro.service.Iservice.ITipoIncidenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoIncidenteServiceImpl implements ITipoIncidenteService {

    private final TipoIncidenteRepository tipoIncidenteRepository;

    @Override
    public List<TipoIncidente> findAll() {
        return tipoIncidenteRepository.findAll();
    }

    @Override
    public TipoIncidente findById(Integer id) {
        return tipoIncidenteRepository.findById(id).orElse(null);
    }
}