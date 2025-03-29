package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;



public interface IAsignaturas {

	public void comenzar();
	public void terminar();
	public ArrayList<Asignatura> get();
	public int getTamano();
	public void insertar(Asignatura asignatura) throws OperationNotSupportedException;
	public Asignatura buscar (Asignatura asignatura);
	public void borrar(Asignatura asignatura) throws OperationNotSupportedException ;
}
