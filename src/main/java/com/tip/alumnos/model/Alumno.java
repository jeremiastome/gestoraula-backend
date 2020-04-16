package com.tip.alumnos.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private boolean asistencia;

    @ManyToOne
    @JsonBackReference
    private Curso curso;

    public Alumno() {}
}
