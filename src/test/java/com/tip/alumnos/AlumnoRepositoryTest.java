package com.tip.alumnos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.ICursoRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {MyApplicationContextInitializer.class})
class AlumnoRepositoryTest {

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
    public void itShouldGetAllStudents() {
        Alumno alumnoTest = new Alumno("Roger", "Federer");
        alumnoRepository.save(alumnoTest);
        List<Alumno> alumnos = alumnoRepository.findAll();

        assertEquals(1, alumnos.size());
        assertEquals("Roger", alumnos.get(0).getNombre());
        assertEquals("Federer", alumnos.get(0).getApellido());
    }

    @Test
    public void itShouldFindForTheStudentsByEmail() {
        String email = "mail1@gmail.com";

        Alumno alumnoTest = new Alumno("Roger", "Federer");
        alumnoTest.setEmailContacto(email);
        alumnoRepository.save(alumnoTest);

        String email2 = "mail12@gmail.com";

        Alumno alumnoTest2 = new Alumno("Roger", "Federer");
        alumnoTest.setEmailContacto(email2);
        alumnoRepository.save(alumnoTest2);

        List<Alumno> alumnos = alumnoRepository.findByEmail(email);

        assertEquals(1, alumnos.size());
        assertEquals("Roger", alumnos.get(0).getNombre());
        assertEquals("Federer", alumnos.get(0).getApellido());
    }
}