package com.github.Radu_A.evaluacion_final.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.github.Radu_A.evaluacion_final.service.IPreguntaService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private IPreguntaService preguntaService;

    @ModelAttribute("tematicas")
    public java.util.List<com.github.Radu_A.evaluacion_final.entity.Tematica> addTematicas() {
        return preguntaService.obtenerTematicas();
    }
}
