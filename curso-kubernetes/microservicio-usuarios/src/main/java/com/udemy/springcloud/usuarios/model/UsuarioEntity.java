package com.udemy.springcloud.usuarios.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer id;
	
	@Column(name = "c_nombre",nullable = false,length = 50)
	private String nombre;
	
	@Column(name = "c_password", nullable = false, length = 50)
	private String password;
	
	@Column(name = "c_email", unique = true, nullable = false, length = 50)
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public UsuarioEntity(Integer id, String nombre, String password, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.email = email;
	}

	public UsuarioEntity() {
		super();
	}
	
	
	
}
