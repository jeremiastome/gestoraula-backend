package com.tip.alumnos.controller;

import com.tip.alumnos.model.Curso;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        cursoRepository.save(curso);
    }

}
