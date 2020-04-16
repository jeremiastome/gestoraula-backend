package com.tip.alumnos.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Alumno> alumnos;

    public Curso() {}
}
