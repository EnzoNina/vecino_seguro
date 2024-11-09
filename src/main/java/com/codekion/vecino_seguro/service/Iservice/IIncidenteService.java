package com.codekion.vecino_seguro.service.Iservice;

import com.codekion.vecino_seguro.model.Incidente;
import com.codekion.vecino_seguro.model.dto.RequestServiceDto;

import java.util.List;

public interface IIncidenteService {

    Incidente reportarIncidente(RequestServiceDto incidente);

    List<Incidente> listarIncidentesPorUsuario(Integer idUsuario);

    List<Incidente> listarIncidentes();

}