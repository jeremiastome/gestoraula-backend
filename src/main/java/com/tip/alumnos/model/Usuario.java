package com.tip.alumnos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private String email;

    @Enumerated(EnumType.STRING)
    private RolDeUsuario rol;

    public Usuario() {}
}
