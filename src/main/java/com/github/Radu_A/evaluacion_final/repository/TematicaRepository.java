package com.github.Radu_A.evaluacion_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.Radu_A.evaluacion_final.entity.Tematica;

@Repository
public interface TematicaRepository extends JpaRepository<Tematica, Long> {

}
