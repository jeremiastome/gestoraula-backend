package com.tip.alumnos.repository;

import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Curso;
import com.tip.alumnos.model.RolDeUsuario;
import com.tip.alumnos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICursoRepository extends JpaRepository<Curso, Integer> {

    @Query("SELECT c FROM Curso c WHERE c.emailDocente = :email")
    List<Curso> findByEmail(String email);

    @Query("SELECT c FROM Curso c JOIN c.alumnos a WHERE a.id =:idAlumno")
    List<Curso> findByAlumnos(int idAlumno);

    //@Query("SELECT curso_curso_id FROM curso_alumnos WHERE alumnos_id =:idAlumno")
    //List<Integer> findId(String idAlumno);
}
