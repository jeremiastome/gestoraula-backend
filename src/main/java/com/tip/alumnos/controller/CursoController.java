package com.tip.alumnos.controller;

import com.tip.alumnos.model.Curso;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CursoController {

    @Autowired
    private ICursoRepository cursoRepository;
    private IAlumnoRepository alumnoRepository;

    @GetMapping("/cursos")
    public List<Curso> cursos() {
        return cursoRepository.findAll();
    }

    @PostMapping("/cursos")
    public void crearCurso(@RequestBody Curso curso) {
        curso.getAlumnos().forEach(x -> {x.setCurso(curso);});
        cursoRepository.save(curso);
    }

}
