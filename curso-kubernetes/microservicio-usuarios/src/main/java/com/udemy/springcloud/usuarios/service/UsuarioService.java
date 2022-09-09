package com.udemy.springcloud.usuarios.service;

import java.util.List;
import java.util.Optional;

import com.udemy.springcloud.usuarios.dto.UsuarioRequest;
import com.udemy.springcloud.usuarios.dto.UsuarioResponse;
import com.udemy.springcloud.usuarios.model.UsuarioEntity;

public interface UsuarioService {

	List<UsuarioResponse>listar();
	UsuarioResponse buscarId(Integer id);
	UsuarioResponse guardarUsuario(UsuarioRequest usuario);
	UsuarioResponse modificarUsuario(UsuarioRequest usuario, Integer id);
	void eliminar(Integer id);
}
