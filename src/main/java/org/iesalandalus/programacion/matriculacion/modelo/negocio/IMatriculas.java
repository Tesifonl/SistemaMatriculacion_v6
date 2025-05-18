package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

public interface IMatriculas {
	

	public void comenzar() throws OperationNotSupportedException;
	public void terminar();
	public ArrayList<Matricula> get() throws OperationNotSupportedException;
	public int getTamano();
	public void insertar(Matricula matricula) throws OperationNotSupportedException;
	public Matricula buscar (Matricula matricula) throws OperationNotSupportedException;
	public void borrar(Matricula matricula) throws OperationNotSupportedException;
	public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException;
	public ArrayList<Matricula> get(String cursoAcademico) throws OperationNotSupportedException;
	public ArrayList<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException;

}