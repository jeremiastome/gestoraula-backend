package com.tip.alumnos.controller;

import com.tip.alumnos.model.RolDeUsuario;
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

    @GetMapping("/usuarios/{rol}")
    public Boolean usuarioConMail(@PathVariable String rol, @RequestParam String email) {
        RolDeUsuario rolEnum = RolDeUsuario.valueOf(rol);
        return usuarioRepository.findByEmail(email, rolEnum) == null;
    }

    @PostMapping("/usuarios")
    public void crearUsuario(@RequestBody Usuario usuario) {
        RolDeUsuario rol = usuario.getRol();
        if(usuarioRepository.findByEmail(usuario.getEmail(), rol)  == null); {
            usuarioRepository.save(usuario);
        }
    }
}
