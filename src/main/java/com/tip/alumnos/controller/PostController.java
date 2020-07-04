package com.tip.alumnos.controller;

import com.tip.alumnos.model.Alumno;
import com.tip.alumnos.model.Curso;
import com.tip.alumnos.model.Post;
import com.tip.alumnos.repository.ICursoRepository;
import com.tip.alumnos.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PostController {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ICursoRepository cursoRepository;

    @GetMapping("/posts")
    public List<Post> posts() {
        return postRepository.findAll();
    }

    @GetMapping("/posts/{cursoId}")
    public List<Post> postsDeCurso(@PathVariable Integer cursoId) {
        Optional <Curso> cursoOp = cursoRepository.findById(cursoId);
        Curso curso = cursoOp.get();
        return curso.getComunicaciones();
    }

    @PostMapping("/posts/{cursoId}")
    public void crearPost(@RequestBody Post post, @PathVariable Integer cursoId) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());
        post.setFecha(date);
        Optional <Curso> cursoOp = cursoRepository.findById(cursoId);
        Curso curso = cursoOp.get();
        curso.getComunicaciones().add(post);
        for(Alumno a : curso.getAlumnos()) {
            a.enviarMailConfirmacionNotificacion(a, curso.getNombre(), post.getCuerpo());
        }
        cursoRepository.save(curso);
    }

}
