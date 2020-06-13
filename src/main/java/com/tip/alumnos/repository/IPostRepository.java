package com.tip.alumnos.repository;

import com.tip.alumnos.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post, Integer> {
}
