package com.practica.instituto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practica.instituto.dao.EstudianteDao;
import com.practica.instituto.model.Estudiante;


@RestController
public class EstudianteController {
	
	@Autowired
	EstudianteDao estudianteDao;

	@RequestMapping(value = "api/Estudiante", method = RequestMethod.GET)
	public List<Estudiante> obtenerListaEstudiantes() {
		return estudianteDao.getEstudiantes();
	}
	
	@RequestMapping(value = "api/register", method = RequestMethod.POST)
	public String registarAlumno(@RequestBody Estudiante estudiante) {
		estudianteDao.createEstudiante(estudiante);
		return "Estudiante creado";
	}
	
	@RequestMapping(value = "api/actualizar", method = RequestMethod.PUT)
	public String modificarEstudiante(@RequestBody Estudiante estudiante) {
		estudianteDao.actualizarEstudiante(estudiante);
		return "Estudiante Actualizado";
	}
	
	@RequestMapping(value = "api/delete/{id}", method = RequestMethod.DELETE)
	public String eliminarAlumno(@PathVariable int id) {
		estudianteDao.eliminarEstudiante(id);
		return "Estudiante eliminado";
	}
}