package com.tip.alumnos.repository;

import com.tip.alumnos.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlumnoRepository extends JpaRepository<Alumno, Integer> {
}
