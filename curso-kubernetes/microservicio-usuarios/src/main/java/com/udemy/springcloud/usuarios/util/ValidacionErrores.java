package com.udemy.springcloud.usuarios.util;

import java.util.Date;
import java.util.Map;

public class ValidacionErrores {

	private Map<String, String> errores;
	
	private Date fecha;

	public Map<String, String> getErrores() {
		return errores;
	}

	public void setErrores(Map<String, String> errores) {
		this.errores = errores;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ValidacionErrores(Map<String, String> errores, Date fecha) {
		super();
		this.errores = errores;
		this.fecha = fecha;
	}
	
	
}
