package com.practica.instituto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practica.instituto.model.Estudiante;

@Repository
@Transactional
public class EstudianteDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Estudiante> getEstudiantes(){
		String query = "FROM Estudiante";
		return entityManager.createQuery(query).getResultList();
	}
	
	public void createEstudiante(Estudiante estudiante) {
		entityManager.merge(estudiante);
	}
	
	public void actualizarEstudiante(Estudiante estudiante) {
		Estudiante actualInfo = entityManager.find(Estudiante.class, estudiante.getId());
		actualInfo.setNombre(estudiante.getNombre());
		actualInfo.setApellido(estudiante.getApellido());
		actualInfo.setEdad(estudiante.getEdad());
		actualInfo.setGenero(estudiante.getGenero());
		actualInfo.setCurso(estudiante.getCurso());
		actualInfo.setMatricula(estudiante.isMatricula());
		entityManager.merge(actualInfo);
		//entityManager.persist(actualInfo);
		
	}
	
	public void eliminarEstudiante(String id) {
		Estudiante user = entityManager.find(Estudiante.class,Integer.parseInt(id));
		entityManager.remove(user);
	}

}
