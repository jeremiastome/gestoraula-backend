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

        if(emailAlumno == null) {
            return;
        }

        emailAlumno.createEmailWith("Confirmación de alta de alumno", alumno.getEmailContacto(),"El alumno "
                + alumno.getNombre() + " " + alumno.getApellido() + " " + "fue dado de alta correctamente.");

        EmailSender emailSender = EmailSender.getInstance();

        emailSender.sendEmail((emailAlumno));
    }

    public void enviarMailConfirmacionDeEliminacion(Alumno alumno, String nombreCurso) {
        Email emailAlumno = new Email();

        if(emailAlumno == null) {
            return;
        }

        emailAlumno.createEmailWith("Alumno removido", alumno.getEmailContacto(),"El alumno "
                + alumno.getNombre() + " " + alumno.getApellido() + " " + "fue removido correctamente del Curso "
                + nombreCurso + ".");

        EmailSender emailSender = EmailSender.getInstance();

        emailSender.sendEmail((emailAlumno));
    }

    public void enviarMailConfirmacionNotificacion(Alumno alumno, String nombreCurso, String comunicacion) {
        Email emailAlumno = new Email();
        String emailContacto = alumno.getEmailContacto();
        System.out.println("");
        System.out.println(emailContacto);
        System.out.println("");
        if(emailContacto == null) {
            return;
        }

        emailAlumno.createEmailWith("Nueva comunicación", alumno.getEmailContacto(),"Una nueva "
                + "comunicación para el Curso " + nombreCurso + " " + "fue creada con la siguiente información: "
                + comunicacion);

        EmailSender emailSender = EmailSender.getInstance();

        emailSender.sendEmail((emailAlumno));
    }
}
