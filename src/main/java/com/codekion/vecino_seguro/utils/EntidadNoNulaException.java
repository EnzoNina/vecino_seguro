package com.codekion.vecino_seguro.utils;

import com.codekion.vecino_seguro.exception.DatoNoIngresadoException;

public class EntidadNoNulaException {

    // Método genérico para verificar si una entidad es null
    public static void verificarEntidadNoNula(Object entidad, String mensajeError) {
        if (entidad == null) {
            throw new DatoNoIngresadoException(mensajeError);
        }
    }


}