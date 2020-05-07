package com.tip.alumnos.repository;

import com.tip.alumnos.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IAsistenciaRepository extends JpaRepository<Asistencia, Integer> {

    @Query("SELECT a FROM Asistencia a WHERE a.curso_id = :cursoId AND a.fechaDeAsistencia = :fecha")
    List<Asistencia> findByDate(int cursoId, Date fecha);

}
