package com.udemy.springcloud.usuarios.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UsuarioRequest {

	@NotBlank
	private String nombre;
	
	@NotBlank
	@Size(min = 1, max = 8)
	private String password;
	
	@NotEmpty
	@Email
	private String email;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UsuarioRequest(String nombre, String password, String email) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.email = email;
	}
	public UsuarioRequest() {
		super();
	}
	
	
	
	
}
