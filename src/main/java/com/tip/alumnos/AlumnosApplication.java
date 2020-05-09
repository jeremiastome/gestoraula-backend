package com.tip.alumnos;

import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Curso;
import com.tip.alumnos.repository.IAlumnoRepository;
import com.tip.alumnos.repository.ICursoRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AlumnosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlumnosApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(ICursoRepository iCursoRepositorym, IAlumnoRepository iAlumnoRepository) {

        return args -> {
            Curso curso1 = new Curso("1° A");
            Curso curso2 = new Curso("1° B");
            Curso curso3 = new Curso("1° C");
            Curso curso4 = new Curso("2° A");
            Curso curso5 = new Curso("2° B");
            Curso curso6 = new Curso("2° C");
            Curso curso7 = new Curso("3° A");
            Curso curso8 = new Curso("3° B");
            Curso curso9 = new Curso("3° C");

            List<Alumno> alumnos = new ArrayList<>();
            alumnos.add(new Alumno("Cristiano", "Ronaldo"));
            alumnos.add(new Alumno("Ricardo", "Fort"));
            alumnos.add(new Alumno("Alberto", "Fernandez"));
            alumnos.add(new Alumno("Roger", "Federer"));
            alumnos.add(new Alumno("Susana", "Gimenez"));
            List<Alumno> alumnos2 = new ArrayList<>();
            alumnos2.add(new Alumno("Mirtha", "Legrand"));
            alumnos2.add(new Alumno("Juan", "Perez"));
            alumnos2.add(new Alumno("Matias", "Cava"));
            alumnos2.add(new Alumno("Andres", "Cruz"));
            alumnos2.add(new Alumno("Gastón", "Veliez"));

            curso1.setAlumnos(alumnos);
            curso2.setAlumnos(alumnos2);
            iCursoRepositorym.save(curso1);
            iCursoRepositorym.save(curso2);
            iCursoRepositorym.save(curso3);
            iCursoRepositorym.save(curso4);
            iCursoRepositorym.save(curso5);
            iCursoRepositorym.save(curso6);
            iCursoRepositorym.save(curso7);
            iCursoRepositorym.save(curso8);
            iCursoRepositorym.save(curso9);
        };
    }
}
