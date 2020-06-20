package com.tip.alumnos.controller;

import com.tip.alumnos.DTO.AlumnoDTO;
import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Asistencia;
import com.tip.alumnos.model.Curso;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.IAsistenciaRepository;
import com.tip.alumnos.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AlumnoController {

    @Autowired
    private IAlumnoRepository alumnoRepository;
    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private IAsistenciaRepository asistenciaRepository;

    @GetMapping("/alumnos/{cursoId}")
    public List<AlumnoDTO> alumnosDeCurso(@PathVariable int cursoId, @RequestParam String fecha) {
        Date aDate = new Date(Long.parseLong(fecha));
        List<Asistencia> response = asistenciaRepository.findByDate(cursoId, aDate);
        Optional<Curso> cursoOp = cursoRepository.findById(cursoId);
        Curso curso = cursoOp.get();
        return getAlumnosDTO(curso.getAlumnos(), response);
    }

    @GetMapping("/alumnosDeCurso/{cursoId}")
    public List<Alumno> getAlumnos(@PathVariable int cursoId) {
        Set<Integer> ids = new HashSet<Integer>();
        Optional<Curso> curso = cursoRepository.findById(cursoId);
        for(Alumno a : curso.get().getAlumnos()) {
            ids.add(a.getId());
        }
        List<Alumno> alumnos;
        if(!ids.isEmpty()) {
            alumnos = alumnoRepository.findByCurso(ids);
        }
        else {
            alumnos = alumnoRepository.findAll();
        }
        return alumnos;
    }


    private List<AlumnoDTO> getAlumnosDTO(List<Alumno> alumnos, List<Asistencia> asistencias) {
        List<AlumnoDTO> alumnosDTO = new ArrayList<>();
        for (Alumno alumno : alumnos) {
            AlumnoDTO alumnoDTO = new AlumnoDTO();
            alumnoDTO.setId(alumno.getId());
            alumnoDTO.setNombre(alumno.getNombre());
            alumnoDTO.setApellido(alumno.getApellido());
            alumnoDTO.setAsistencia(getAsistencia(alumno.getId(), asistencias));
            alumnosDTO.add(alumnoDTO);
        }

        return alumnosDTO;
    }

    private Asistencia getAsistencia(int id, List<Asistencia> asistencias) {
        for (Asistencia asistencia : asistencias) {
            if(id == asistencia.getAlumnoId()) {
                return asistencia;
            }
        }
        return null;
    }

    @GetMapping("/alumnos")
    public List<Alumno> alumnos() {
        return alumnoRepository.findAll();
    }

    @GetMapping("/alumnosRegistrados/{email}")
    public List<Alumno> alumnos(@PathVariable String email) {
        return alumnoRepository.findByEmail(email);
    }

    @PostMapping("/alumnos")
    public ResponseEntity crearAlumno(@RequestBody Alumno alumno) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        if(alumnoRepository.findByDni(alumno.getDni())  == null) {
            alumnoRepository.save(alumno);
        }
        else {
            return new ResponseEntity<String>(
                    "Ya existe un alumno con el número de documento ingresado",
                    headers,
                    HttpStatus.CONFLICT
            );
        }
        Alumno nuevoalumno = alumnoRepository.findByDni(alumno.getDni());
        alumno.enviarMailConfirmacion(alumno);
        return new ResponseEntity<Alumno>(
                nuevoalumno,
                headers,
                HttpStatus.OK
        );
    }

    @PutMapping("/alumnos/{emailContacto}")
    public ResponseEntity actualizarAlumno(@RequestBody Alumno alumno, @PathVariable String emailContacto, @RequestParam String dni) {
        Alumno alumnoActualizado;
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        if(String.valueOf(alumno.getDni()) != String.valueOf(dni) && alumnoRepository.findByDni(alumno.getDni()) == null) {
            alumnoActualizado = alumnoRepository.saveAndFlush(alumno);
        }
        else {
            return new ResponseEntity<String>(
                    "Ya existe un alumno con el número de documento ingresado",
                    headers,
                    HttpStatus.CONFLICT
            );
        }

        if(emailContacto != alumno.getEmailContacto()) {
            alumno.enviarMailConfirmacion(alumno);
        }
        return new ResponseEntity<Alumno>(
                alumnoActualizado,
                headers,
                HttpStatus.OK
        );
    }

    @PutMapping("/removerAlumno/{cursoId}")
    public void removerDeCurso(@PathVariable Integer cursoId, @RequestParam Integer alumnoId) {
        remover(cursoId, alumnoId);
    }

    public void remover(Integer cursoId, Integer alumnoId) {
        Optional<Curso> cursoOp = cursoRepository.findById(cursoId);
        Curso curso = cursoOp.get();
        List<Alumno> alumnos = new ArrayList<Alumno>();
        for(Alumno a : curso.getAlumnos()) {
            if(alumnoId != a.getId()) {
                alumnos.add(a);
            }
        }
        curso.setAlumnos(alumnos);

        List<Asistencia> asistencias = asistenciaRepository.findByAlumno(alumnoId);
        asistenciaRepository.deleteAll(asistencias);

        cursoRepository.save(curso);
    }
}
