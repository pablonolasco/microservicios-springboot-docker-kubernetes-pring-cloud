package com.udemy.springcloud.cursos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.springcloud.cursos.dto.CursoRequest;
import com.udemy.springcloud.cursos.dto.CursoResponse;
import com.udemy.springcloud.cursos.service.impl.CursoServiceImpl;

@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoServiceImpl cursoServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<CursoResponse>>obtenterCursos(){
		return new ResponseEntity<List<CursoResponse>>(cursoServiceImpl.listarCursos(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CursoResponse>obtenterCursos(@PathVariable Integer id){
		return new ResponseEntity<CursoResponse>(cursoServiceImpl.obtenerCurso(id),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?>crearCurso(@Valid @RequestBody CursoRequest cursoRequest, BindingResult result){
		Map<String, String> errores= new HashMap<>();
		if (result.hasErrors()) {
			result.getFieldErrors().forEach(e->{
				errores.put(e.getField(), "El campo "+ e.getField()+" "+ e.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(errores);
		}
		return new ResponseEntity<CursoResponse>(cursoServiceImpl.crearCurso(cursoRequest),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>actualizarCurso(@Valid @RequestBody CursoRequest cursoRequest,BindingResult result, @PathVariable Integer id){
		
		if (result.hasErrors()) {
			return validar(result);
		}
		return new ResponseEntity<CursoResponse>(cursoServiceImpl.modificarCurso(id, cursoRequest),HttpStatus.OK);
	}

	private ResponseEntity<?> validar(BindingResult result) {
		Map<String, String> errores= new HashMap<>();
		result.getFieldErrors().forEach(e->{
			errores.put(e.getField(), "El campo "+ e.getField()+" "+ e.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>desactivarCurso(@PathVariable Integer id){
	
		cursoServiceImpl.eliminarCurso(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
