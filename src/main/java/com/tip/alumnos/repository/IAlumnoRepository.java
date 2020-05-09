package com.tip.alumnos.repository;

import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IAlumnoRepository extends JpaRepository<Alumno, Integer> {

    @Query("SELECT a FROM Alumno a WHERE a.id NOT IN (SELECT b.alumnos FROM Curso b WHERE b.curso_id != :cursoId)")
    List<Alumno> findByCurso(int cursoId);
}
