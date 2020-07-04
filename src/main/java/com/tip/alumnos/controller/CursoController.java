package com.tip.alumnos.controller;

import com.tip.alumnos.DTO.AlumnoDTO;
import com.tip.alumnos.DTO.CursoDTO;
import com.tip.alumnos.Email.Email;
import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Asistencia;
import com.tip.alumnos.model.Curso;
import com.tip.alumnos.model.Evento;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CursoController {

    @Autowired
    private ICursoRepository cursoRepository;

    @GetMapping("/cursos/{email}")
    public List<CursoDTO> cursos(@PathVariable String email) {
        List<CursoDTO> cursosDTO = new ArrayList<>();
        List<Curso> cursos = cursoRepository.findByEmail(email);
        for (Curso curso : cursos) {
            CursoDTO cursoDTO = new CursoDTO();
            cursoDTO.setId(curso.getCurso_id());
            cursoDTO.setNombre(curso.getNombre());
            cursosDTO.add(cursoDTO);
        }
        return cursosDTO;
    }

    @GetMapping("/eventos/{cursoId}")
    public List<Evento> getEventos(@PathVariable int cursoId) {
        Curso cursoAModificar = cursoRepository.findById(cursoId).get();
        return cursoAModificar.getEventos();
    }

    @PostMapping("/cursos")
    public Curso crearCurso(@RequestBody Curso curso) {
        curso.calcularCantidadDeDiasDeClase();
        curso.setEstado();
        Curso nuevoCurso = cursoRepository.saveAndFlush(curso);
        return nuevoCurso;
    }

    @PutMapping("/cursos/{id}")
    public void modificarCurso(@RequestBody List<Asistencia> asistencias, @PathVariable int id) {
        Curso cursoAModificar = cursoRepository.findById(id).get();
        cursoAModificar.getAsistencias().addAll(asistencias);
        cursoRepository.save(cursoAModificar);
    }

    @PutMapping("/cursosAlumnos/{id}")
    public void modificarCursoAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable int id) {
        Curso cursoAModificar = cursoRepository.findById(id).get();
        cursoAModificar.getAlumnos().addAll(alumnos);
        cursoRepository.save(cursoAModificar);
    }

    @PutMapping("/evento/{id}")
    public List<EmailResponse> modificarEventos(@RequestBody Evento evento, @PathVariable int id) {
        List<EmailResponse> res = new ArrayList<>();
        Curso cursoAModificar = cursoRepository.findById(id).get();
        cursoAModificar.getEventos().add(evento);
        cursoRepository.save(cursoAModificar);
        Set<String> emails = new HashSet<>();

        for (Alumno alumno : cursoAModificar.getAlumnos()) {
            if(alumno.getEmailContacto() != null && !emails.contains(alumno.getEmailContacto())) {
                EmailResponse emailResponse = new EmailResponse(alumno.getEmailContacto());
                res.add(emailResponse);
            }
        }
        return res;
    }


    public class EmailResponse {
        public String email;

        public EmailResponse(String email) {
            this.email = email;
        }
    }
}
