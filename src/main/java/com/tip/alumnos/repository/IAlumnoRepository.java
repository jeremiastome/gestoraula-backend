package com.tip.alumnos.repository;

import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IAlumnoRepository extends JpaRepository<Alumno, Integer> {


    @Query("SELECT a FROM Alumno a WHERE a.id NOT IN :ids")
    List<Alumno> findByCurso(Set<Integer> ids);

    @Query("SELECT a FROM Alumno a WHERE a.emailContacto = :email")
    List<Alumno> findByEmail(String email);

    @Query("SELECT a FROM Alumno a WHERE a.dni = :dni")
    Alumno findByDni(Integer dni);
}
