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
	
	public Estudiante getEstudianteActualizar(int id) {
		Estudiante infoEstudiante =  entityManager.find(Estudiante.class, id);
		return infoEstudiante;
	}
	
	public void createEstudiante(Estudiante estudiante) {
		entityManager.merge(estudiante);
	}
	
	public void actualizarEstudiante(Estudiante estudiante) {
		entityManager.merge(estudiante);
		//entityManager.persist(actualInfo);
		
	}
	
	public void eliminarEstudiante(int id) {
		Estudiante user = entityManager.find(Estudiante.class,id);
		entityManager.remove(user);
	}
	


}
