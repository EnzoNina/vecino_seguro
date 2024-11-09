package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.TiposRelacion;
import com.codekion.vecino_seguro.repository.TipoRelacionRepository;
import com.codekion.vecino_seguro.service.Iservice.ITipoRelacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoRelacionServiceImpl implements ITipoRelacionService {

    private final TipoRelacionRepository tipoRelacionRepository;

    @Override
    public List<TiposRelacion> listarTiposRelacion() {
        return tipoRelacionRepository.findAll();
    }

    @Override
    public TiposRelacion buscarTipoRelacion(Integer id) {
        return tipoRelacionRepository.findById(id).orElse(null);
    }
}