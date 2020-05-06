package com.tip.alumnos.repository;

import com.tip.alumnos.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAsistenciaRepository extends JpaRepository<Asistencia, Integer> {
}
