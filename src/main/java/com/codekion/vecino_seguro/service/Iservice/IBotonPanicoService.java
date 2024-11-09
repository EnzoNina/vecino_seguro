package com.codekion.vecino_seguro.service.Iservice;

import com.codekion.vecino_seguro.model.BotonPanico;
import com.codekion.vecino_seguro.model.dto.RequestBotonPanicoDto;

public interface IBotonPanicoService {

    BotonPanico activarBotonPanico(RequestBotonPanicoDto botonPanico);

}