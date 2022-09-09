package com.udemy.springcloud.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.springcloud.usuarios.model.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

	Optional<UsuarioEntity> findByEmailAndPassword(String email, String password);
	Optional<UsuarioEntity>findByEmail(String email);
	
}
