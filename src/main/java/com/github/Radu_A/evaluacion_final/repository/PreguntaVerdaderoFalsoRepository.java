package com.github.Radu_A.evaluacion_final.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.Radu_A.evaluacion_final.entity.PreguntaVerdaderoFalso;

@Repository
public interface PreguntaVerdaderoFalsoRepository extends JpaRepository<PreguntaVerdaderoFalso, Long> {
    List<PreguntaVerdaderoFalso> findByTematicaId(Long tematicaId);
}
