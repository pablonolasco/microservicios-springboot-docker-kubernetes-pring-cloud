package com.udemy.springcloud.cursos.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.udemy.springcloud.cursos.dto.CursoRequest;
import com.udemy.springcloud.cursos.dto.CursoResponse;
import com.udemy.springcloud.cursos.model.CursoEntity;
import com.udemy.springcloud.cursos.repository.CursoRepository;
import com.udemy.springcloud.cursos.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService {

	private CursoRepository cursoRepository;
	
	
	@Autowired
	public CursoServiceImpl(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}

	@Override
	public List<CursoResponse> listarCursos() {
		List<CursoEntity>cursosEntities=cursoRepository.findAll();
		List<CursoResponse>cursosResponse= new ArrayList<>();
		for (CursoEntity cursoEntity : cursosEntities) {
			CursoResponse cursoResponse= new CursoResponse();
			cursoResponse.setId(cursoEntity.getId());
			cursoResponse.setNombre(cursoEntity.getNombre());
			cursosResponse.add(cursoResponse);
		}
		return cursosResponse;
	}

	@Override
	public CursoResponse obtenerCurso(Integer id) {
		CursoEntity curso=cursoRepository.findById(id)
				.orElseThrow(()-> 
				new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El id %d no se encontro", id)));
		CursoResponse cursoResponse= new CursoResponse();
		
		BeanUtils.copyProperties(curso, cursoResponse);
		return cursoResponse;
	}

	@Override
	public CursoResponse crearCurso(CursoRequest cursoRequest) {
		CursoEntity cursoEntity= new CursoEntity();
		CursoResponse cursoResponse= new CursoResponse();
		BeanUtils.copyProperties(cursoRequest, cursoEntity);
		BeanUtils.copyProperties(cursoRepository.saveAndFlush(cursoEntity),cursoResponse);
		return cursoResponse;
	}

	@Override
	public CursoResponse modificarCurso(Integer id, CursoRequest cursoRequest) {
		CursoEntity curso=cursoRepository.findById(id)
				.orElseThrow(()-> 
				new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El id %d no se encontro", id)));
		CursoResponse cursoResponse= new CursoResponse();
		curso.setNombre(cursoRequest.getNombre());
		
		BeanUtils.copyProperties(cursoRepository.save(curso), cursoResponse);
		return cursoResponse;
	}

	@Override
	public void eliminarCurso(Integer id) {
		CursoEntity curso=cursoRepository.findById(id)
				.orElseThrow(()-> 
				new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("El id %d no se encontro", id)));
		cursoRepository.deleteById(id);
	}

}
