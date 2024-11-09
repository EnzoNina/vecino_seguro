package com.codekion.vecino_seguro.service.Iservice;

import com.codekion.vecino_seguro.model.TiposRelacion;

import java.util.List;

public interface ITipoRelacionService {

    List<TiposRelacion> listarTiposRelacion();

    TiposRelacion buscarTipoRelacion(Integer id);

}