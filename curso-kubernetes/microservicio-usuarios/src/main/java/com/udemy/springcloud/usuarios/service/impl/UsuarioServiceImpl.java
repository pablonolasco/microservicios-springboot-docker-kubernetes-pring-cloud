package com.udemy.springcloud.usuarios.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.udemy.springcloud.usuarios.dto.UsuarioRequest;
import com.udemy.springcloud.usuarios.dto.UsuarioResponse;
import com.udemy.springcloud.usuarios.execeptions.BadRequestException;
import com.udemy.springcloud.usuarios.execeptions.ResourceNotFoundException;
import com.udemy.springcloud.usuarios.model.UsuarioEntity;
import com.udemy.springcloud.usuarios.repository.UsuarioRepository;
import com.udemy.springcloud.usuarios.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioResponse> listar() {
		List<UsuarioEntity>usuarios=usuarioRepository.findAll();
		List<UsuarioResponse>listarResponse= new ArrayList<>();
		for (UsuarioEntity usuario : usuarios) {
			UsuarioResponse usuarioResponse= new UsuarioResponse();
			usuarioResponse.setId(usuario.getId());
			usuarioResponse.setNombre(usuario.getNombre());
			usuarioResponse.setEmail(usuario.getEmail());
			listarResponse.add(usuarioResponse);
		}
		return listarResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioResponse buscarId(Integer id) {
		Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(id);
		UsuarioResponse usuarioResponse= new UsuarioResponse();
		BeanUtils.copyProperties(usuarioEncontrado.get(), usuarioResponse);
		return usuarioResponse;
		
	}

	@Override
	@Transactional
	public UsuarioResponse guardarUsuario(UsuarioRequest usuarioRequest) {
		// TODO Auto-generated method stub
		UsuarioEntity usuario= new UsuarioEntity();
		Optional<UsuarioEntity> existe=usuarioRepository.findByEmail(usuarioRequest.getEmail());
		
		if (existe.isPresent()) {
			//throw new RuntimeException(String.format("El correo ya esta registrado", usuarioRequest.getEmail()));
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "El corre ya esta registrado");
		}
		UsuarioResponse usuarioResponse= new UsuarioResponse();
		BeanUtils.copyProperties(usuarioRequest, usuario);
		BeanUtils.copyProperties(usuarioRepository.saveAndFlush(usuario), usuarioResponse);
		
		return usuarioResponse;
	}

	@Override
	@Transactional
	public UsuarioResponse modificarUsuario(UsuarioRequest usuario, Integer id) {
		Optional<UsuarioEntity> usuarioEncontrado = usuarioRepository.findById(id);
		if (!usuarioEncontrado.isPresent()) {
			throw new ResourceNotFoundException("Usuario","id",String.valueOf(id));
		}
		
		Optional<UsuarioEntity> existe=usuarioRepository.findByEmail(usuarioEncontrado.get().getEmail());
		
		if (!usuario.getEmail().equalsIgnoreCase(usuarioEncontrado.get().getEmail()) &&  usuarioEncontrado.isPresent()) {
			//throw new RuntimeException(String.format("El correo ya esta registrado", usuarioRequest.getEmail()));
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "El corre ya esta registrado");
		}
		
		usuarioEncontrado.get().setNombre(usuario.getNombre());
		usuarioEncontrado.get().setPassword(usuario.getPassword());
		usuarioEncontrado.get().setEmail(usuario.getEmail());
		UsuarioResponse usuarioResponse= new UsuarioResponse();
		BeanUtils.copyProperties(usuarioRepository.save(usuarioEncontrado.get()), usuarioResponse);

		return usuarioResponse;
	}

	@Override
	@Transactional
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id);
	}

}
