package com.practica.instituto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "estudiante")
public class Estudiante extends Persona{
	
	@Column(name = "curso")
	private String curso;
	
	@Column(name = "matricula")
	private boolean matricula;
	
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public boolean isMatricula() {
		return matricula;
	}
	public void setMatricula(boolean matricula) {
		this.matricula = matricula;
	}
	

}
