package com.tip.alumnos.controller;

import com.tip.alumnos.model.Usuario;
import com.tip.alumnos.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<Usuario> usuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuarios/{email}")
    public Boolean usuarioConMail(@PathVariable String email) {
        return usuarioRepository.findByEmail(email) == null;
    }

    @PostMapping("/usuarios")
    public void crearUsuario(@RequestBody Usuario usuario) {
        if(usuarioRepository.findByEmail(usuario.getEmail()) == null); {
            usuarioRepository.save(usuario);
        }
    }
}
