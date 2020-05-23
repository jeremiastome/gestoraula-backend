package com.tip.alumnos.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int curso_id;
    @Temporal(TemporalType.DATE)
    private Date fechaDeAsistencia;
    private int alumnoId;

    public Asistencia() {}
}
