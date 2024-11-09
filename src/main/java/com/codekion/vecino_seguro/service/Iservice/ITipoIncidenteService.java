package com.codekion.vecino_seguro.service.Iservice;


import com.codekion.vecino_seguro.model.TipoIncidente;

import java.util.List;

public interface ITipoIncidenteService {

    List<TipoIncidente> findAll();

    TipoIncidente findById(Integer id);

}