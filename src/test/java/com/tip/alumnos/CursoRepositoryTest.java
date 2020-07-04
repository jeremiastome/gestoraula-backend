
package com.tip.alumnos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Curso;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.ICursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {MyApplicationContextInitializer.class})
class CursoRepositoryTest {

    @Autowired
    public IAlumnoRepository alumnoRepository;
    @Autowired
    public ICursoRepository cursoRepository;

    @BeforeEach
    public void setup() {
        cursoRepository.deleteAll();
        alumnoRepository.deleteAll();
    }

    @Test
    public void getClassAssociatedWithATeacher() {
        Date fechaInicio = new Date();
        Date fechaFin = fechaInicio;
        String email = "mail1@gmail.com";
        String email2 = "mail12@gmail.com";
        Curso curso1 = new Curso("1° A", fechaInicio, fechaFin, email);
        Curso curso2 = new Curso("1° B", fechaInicio, fechaFin, email);
        Curso curso3 = new Curso("1° C", fechaInicio, fechaFin, email2);
        cursoRepository.save(curso1);
        cursoRepository.save(curso2);
        cursoRepository.save(curso3);

        List<Curso> cursos = cursoRepository.findByEmail(email);

        assertEquals(2, cursos.size());
    }
}
