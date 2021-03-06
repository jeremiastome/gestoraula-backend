package com.tip.alumnos.repository;

import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICursoRepository extends JpaRepository<Curso, Integer> {

}
