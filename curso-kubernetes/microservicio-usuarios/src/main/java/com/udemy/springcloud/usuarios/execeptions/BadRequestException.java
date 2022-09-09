package com.udemy.springcloud.usuarios.execeptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus estado;
	private String mensaje;
	
	public BadRequestException(HttpStatus estado, String mensaje) {
		super(mensaje);
		this.estado = estado;
		this.mensaje = mensaje;
	}

	public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}