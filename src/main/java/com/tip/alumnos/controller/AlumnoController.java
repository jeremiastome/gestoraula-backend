package com.tip.alumnos.controller;

import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AlumnoController {

    @Autowired
    private ICursoRepository cursoRepository;
    private IAlumnoRepository alumnoRepository;

    @GetMapping("/alumnos")
    public List<Alumno> alumnos() {
        return alumnoRepository.findAll();
    }

}
