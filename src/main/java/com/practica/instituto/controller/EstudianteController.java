package com.practica.instituto.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.practica.instituto.dao.EstudianteDao;
import com.practica.instituto.excepciones.ConnectException;
import com.practica.instituto.model.Estudiante;



@RestController
public class EstudianteController {
	
	@Autowired
	EstudianteDao estudianteDao;
	
	Map<String,String> responseBody = new HashMap<>();

	@RequestMapping(value = "api/Estudiante", method = RequestMethod.GET)
	public ResponseEntity<Object> obtenerListaEstudiantes() {
		try {		
			return new ResponseEntity<Object>(estudianteDao.getEstudiantes(),HttpStatus.OK);	
		} catch (Exception ex) {
			responseBody.put("Status","FAILED");
		    responseBody.put("Message",ex.getCause().toString());
		    return  new ResponseEntity<Object>(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "api/register", method = RequestMethod.POST)
	public ResponseEntity<Object> registarAlumno(@RequestBody Estudiante estudiante) {
		try {
			
			if (estudiante.getNombre() == "" || estudiante.getNombre() == null) {
				responseBody.put("Status","Failed");
			    responseBody.put("Message","El campos nombre es oblogatorio");
				return  new ResponseEntity<Object>(responseBody,HttpStatus.BAD_REQUEST);
			}
			if (estudiante.getApellido() == "" || estudiante.getApellido() == null) {
				responseBody.put("Status","Failed");
			    responseBody.put("Message","El campos apellido es oblogatorio");
				return  new ResponseEntity<Object>(responseBody,HttpStatus.BAD_REQUEST);
			}
			estudianteDao.createEstudiante(estudiante);
			responseBody.put("Status","OK");
		    responseBody.put("Message","Estudiante registrado con exito!");
			return  new ResponseEntity<Object>(responseBody,HttpStatus.OK);
		} catch (Exception ex) {
			responseBody.put("Status","FAILED");
		    responseBody.put("Message",ex.getCause().toString());
			return  new ResponseEntity<Object>(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "api/actualizar", method = RequestMethod.PUT)
	public  ResponseEntity<Object> modificarEstudiante(@RequestBody Estudiante estudiante) {
		
		try {
			estudianteDao.actualizarEstudiante(estudiante);
			responseBody.put("Status","OK");
		    responseBody.put("Message","Datos del estudiante actualizado");
			return  new ResponseEntity<Object>(responseBody,HttpStatus.OK);
		} catch (Exception ex) {
			responseBody.put("Status","FAILED");
		    responseBody.put("Message",ex.getCause().toString());
			return  new ResponseEntity<Object>(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "api/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> eliminarAlumno(@PathVariable String id) {
		try {
			if (id.matches("[0-9]*")) {
				  estudianteDao.eliminarEstudiante(id);
				  responseBody.put("Status","OK");
				  responseBody.put("Message","Estudiante eliminado con exito");
				  return new ResponseEntity<Object>(responseBody,HttpStatus.OK);
			}else {
				  responseBody.put("Status","FAILED");
				  responseBody.put("Message","Validar estructuras de los datos enviados");
				  return new ResponseEntity<Object>(responseBody,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
		    responseBody.put("Status","FAILED");
		    responseBody.put("Message",ex.getCause().toString());
		    return new ResponseEntity<Object>(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);

		}	
       
	}
	
}