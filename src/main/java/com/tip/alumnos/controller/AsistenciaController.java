package com.tip.alumnos.controller;

import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Asistencia;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.IAsistenciaRepository;
import com.tip.alumnos.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AsistenciaController {

    @Autowired
    private IAsistenciaRepository asistenciaRepository;

    @DeleteMapping("/asistencias")
    public void borrarAsistencias(@RequestBody List<Asistencia> asistencias) {
        asistenciaRepository.deleteAll(asistencias);
    }

    @GetMapping("/asistencias/{cursoId}")
    public List<Asistencia> asistenciasPorFecha(@PathVariable int cursoId, @RequestParam String fecha) {

        Date aDate = new Date(Long.parseLong(fecha));

        List<Asistencia> response = asistenciaRepository.findByDate(cursoId, aDate);
        return response;
    }
}
