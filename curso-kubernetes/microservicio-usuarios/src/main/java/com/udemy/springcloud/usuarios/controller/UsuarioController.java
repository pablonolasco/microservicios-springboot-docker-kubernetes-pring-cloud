package com.udemy.springcloud.usuarios.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.springcloud.usuarios.dto.UsuarioRequest;
import com.udemy.springcloud.usuarios.dto.UsuarioResponse;
import com.udemy.springcloud.usuarios.service.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponse>>listarUsuario(){
		return new ResponseEntity<List<UsuarioResponse>>(usuarioServiceImpl.listar(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>buscarUsuario(@PathVariable Integer id){
		return new ResponseEntity<>(usuarioServiceImpl.buscarId(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioResponse>crearUsuario(@Valid @RequestBody UsuarioRequest request){
		return new ResponseEntity<UsuarioResponse>( usuarioServiceImpl.guardarUsuario(request) ,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponse>modificarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest,@PathVariable Integer id){
		return new ResponseEntity<>( usuarioServiceImpl.modificarUsuario(usuarioRequest, id) ,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>borrarUsuario(@PathVariable Integer id){
		usuarioServiceImpl.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
