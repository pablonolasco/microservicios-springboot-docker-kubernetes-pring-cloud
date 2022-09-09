package com.udemy.springcloud.cursos.service;

import java.util.List;

import com.udemy.springcloud.cursos.dto.CursoRequest;
import com.udemy.springcloud.cursos.dto.CursoResponse;

public interface CursoService {

	List<CursoResponse>listarCursos();
	CursoResponse obtenerCurso(Integer id);
	CursoResponse crearCurso(CursoRequest cursoRequest);
	CursoResponse modificarCurso(Integer id, CursoRequest cursoRequest);
	void eliminarCurso(Integer id);
	
}
