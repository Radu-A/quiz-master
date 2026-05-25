package com.github.Radu_A.evaluacion_final.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.github.Radu_A.evaluacion_final.entity.Pregunta;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long>, JpaSpecificationExecutor<Pregunta> {
    List<Pregunta> findByTematicaId(Long tematicaId);
    Optional<Pregunta> findById(Long id);
}
