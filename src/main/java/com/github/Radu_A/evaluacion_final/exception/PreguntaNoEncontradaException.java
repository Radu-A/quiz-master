package com.github.Radu_A.evaluacion_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PreguntaNoEncontradaException extends RuntimeException {

    public PreguntaNoEncontradaException(Long id) {
        super("Pregunta con id " + id + " no encontrada");
    }
}
