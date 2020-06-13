package com.tip.alumnos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int curso_id;

    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer cantidadDeDiasDeClase;
    private EstadoDeCurso estado;
    private String emailDocente;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> comunicaciones;

    @ManyToMany
    public List<Alumno> alumnos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="curso_id")
    private List<Asistencia> asistencias;

    public Curso() {}

    public Curso(String nombre, Date fechaInicio, Date fechaFin, String email) {
        this.nombre = nombre;
        this.emailDocente = email;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public void calcularCantidadDeDiasDeClase() {
        cantidadDeDiasDeClase = DateUtils.getClassDays(fechaInicio, fechaFin);
    }

    public void setEstado() {
        if(fechaFin.after(new Date())) {
            estado = EstadoDeCurso.EnCurso;
        }
        else {
            estado = EstadoDeCurso.Finalizado;
        }
    }
}
