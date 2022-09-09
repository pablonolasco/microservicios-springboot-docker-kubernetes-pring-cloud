package com.udemy.springcloud.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.springcloud.cursos.model.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
 
}
