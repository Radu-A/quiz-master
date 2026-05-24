package com.github.Radu_A.evaluacion_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionUnica;

@Repository
public interface PreguntaSeleccionUnicaRepository extends JpaRepository<PreguntaSeleccionUnica, Long> {
    List<PreguntaSeleccionUnica> findByTematicaId(Long tematicaId);
}
