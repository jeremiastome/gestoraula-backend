package com.tip.alumnos.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CursoDTO {

    private int id;
    private String nombre;
    private List<AlumnoDTO> alumnos;
}
