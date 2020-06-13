package com.tip.alumnos.model;

import com.tip.alumnos.Email.Email;
import com.tip.alumnos.Email.EmailSender;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private Integer dni;

    private String emailContacto;
    private Integer telefono;
    private Integer telefonoMovil;

    public Alumno() {}

    public Alumno(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public void enviarMailConfirmacion(Alumno alumno) {
        Email emailAlumno = new Email();

        emailAlumno.createEmailWith("Confirmación de alta de alumno", alumno.getEmailContacto(),"El alumno "
                + alumno.getNombre() + " " + alumno.getApellido() + " " + "fue dado de alta correctamente.");

        EmailSender emailSender = EmailSender.getInstance();

        emailSender.sendEmail((emailAlumno));
    }
}
