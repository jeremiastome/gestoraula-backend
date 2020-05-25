package com.tip.alumnos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.ICursoRepository;
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

    @Test
    void testBefore() {
    }

    @Test
    public void itShouldGetAllStudents() {
        Alumno alumnoTest = new Alumno("Roger", "Federer");
        alumnoRepository.save(alumnoTest);
        List<Alumno> alumnos = alumnoRepository.findAll();

        assertEquals(10, alumnos.size());
    }
}