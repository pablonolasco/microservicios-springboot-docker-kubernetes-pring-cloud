package com.udemy.springcloud.usuarios.util;

import java.util.Date;

public class ErrorsMessageResponse {

	private Date timestamp;
	
	private String mensaje;
	
	private String detalles;

	private long estatus;

	
	public ErrorsMessageResponse(Date timestamp, String mensaje, String detalles, long estatus) {
		super();
		this.timestamp = timestamp;
		this.mensaje = mensaje;
		this.detalles = detalles;
		this.estatus = estatus;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public long getEstatus() {
		return estatus;
	}

	public void setEstatus(long estatus) {
		this.estatus = estatus;
	}
	
	
	
	
}
