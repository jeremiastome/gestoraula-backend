    package com.tip.alumnos.repository;


import com.tip.alumnos.model.RolDeUsuario;
import com.tip.alumnos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.rol = :rol")
    Usuario findByEmail(String email, RolDeUsuario rol);
}
