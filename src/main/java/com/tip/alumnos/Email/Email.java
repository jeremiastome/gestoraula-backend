package com.tip.alumnos.Email;

import com.tip.alumnos.Excepciones.InvalidEmailException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {

    private String user;
    private String password;
    private String subject;
    private String receiver;
    private String message;

    public Email() {

        this.user = "gdealumnos@gmail.com";
        this.password = "gestiontip";
    }

    public void createEmailWith(String subject, String receiver, String message) {
        // Chequea si un mail es valido y lo compone con los campos que recibe como parametro.
        validEmail(receiver);

        setSubject(subject);
        setReceiver(receiver);
        setMessage(message);
    }

    public void validEmail(String newEmail) {
        // Chequea si un mail es valido mediante expresiones regulares.
        if (!newEmail.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
            throw new InvalidEmailException("Invalid email");
    }

}