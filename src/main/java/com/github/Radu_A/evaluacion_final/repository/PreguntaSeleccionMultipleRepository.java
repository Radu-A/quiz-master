package com.github.Radu_A.evaluacion_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.Radu_A.evaluacion_final.entity.PreguntaSeleccionMultiple;

@Repository
public interface PreguntaSeleccionMultipleRepository extends JpaRepository<PreguntaSeleccionMultiple, Long> {
    List<PreguntaSeleccionMultiple> findByTematicaId(Long tematicaId);
}
