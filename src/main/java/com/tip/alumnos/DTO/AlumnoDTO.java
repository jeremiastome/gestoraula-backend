package com.tip.alumnos.DTO;

import com.tip.alumnos.model.Asistencia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoDTO {

    private int id;
    private String nombre;
    private String apellido;
    private Asistencia asistencia;
}
