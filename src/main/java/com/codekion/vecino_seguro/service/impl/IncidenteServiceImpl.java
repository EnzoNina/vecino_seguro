package com.codekion.vecino_seguro.service.impl;

import com.codekion.vecino_seguro.model.Incidente;
import com.codekion.vecino_seguro.model.dto.RequestServiceDto;
import com.codekion.vecino_seguro.repository.IncidenteRepository;
import com.codekion.vecino_seguro.service.Iservice.IIncidenteService;
import com.codekion.vecino_seguro.service.Iservice.ITipoIncidenteService;
import com.codekion.vecino_seguro.service.Iservice.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidenteServiceImpl implements IIncidenteService {

    private final IncidenteRepository incidenteRepository;
    private final ITipoIncidenteService tipoIncidenteService;
    private final IUsuarioService usuarioService;

    @Override
    public Incidente reportarIncidente(RequestServiceDto incidente) {

        //Creamos el objeto incidente
        Incidente incidenteObj = new Incidente();
        incidenteObj.setUsuario(usuarioService.findById(Integer.parseInt(incidente.getId_usuario())).get());
        incidenteObj.setTipoIncidente(tipoIncidenteService.findById(Integer.parseInt(incidente.getId_tipo_incidente())));
        incidenteObj.setDescripcion(incidente.getDescripcion());
        incidenteObj.setLatitud(new BigDecimal(incidente.getLatitud()));
        incidenteObj.setLongitud(new BigDecimal(incidente.getLongitud()));
        //Guardamos el incidente
        return incidenteRepository.save(incidenteObj);
    }

    @Override
    public List<Incidente> listarIncidentesPorUsuario(Integer idUsuario) {
        return incidenteRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public List<Incidente> listarIncidentes() {
        return incidenteRepository.findAll();
    }
}
