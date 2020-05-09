package com.tip.alumnos.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int curso_id;

    private String nombre;
    @ManyToMany(cascade = CascadeType.ALL)
    public List<Alumno> alumnos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="curso_id")
    private List<Asistencia> asistencias;

    public Curso() {}

    public Curso(String nombre) {
        this.nombre = nombre;
    }

}
